package hu.tilos.radio.backend.converters;


import hu.radio.tilos.model.EntityWithTag;
import hu.radio.tilos.model.Episode;
import hu.radio.tilos.model.Tag;
import hu.radio.tilos.model.type.TagType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.print.Doc;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to process @tag and #tags.
 */
public class TagUtil {

    public static final String GENERIC_SIMPLE = "(?<![&#A-Z0-9a-záíűőüöúóéÁÍŰŐÜÖÚÓÉ])#([a-zA-Z&;áíűőüöúóéÁÍŰŐÜÖÚÓÉ][\\w&;áíűőüöúóéÁÍŰŐÜÖÚÓÉ-]+)";

    public static final String GENERIC_COMPLEX = "\\#\\{(.+?)\\}";

    public static final String PERSON_SIMPLE = "(?<![\\w@])\\@([\\w&;áíűőüöúóéÁÍŰŐÜÖÚÓÉ-]+)";

    public static final String PERSON_COMPLEX = "(?<![\\w@])\\@\\{(.+?)\\}";

    public static final Pattern HEX_CODE = Pattern.compile("[ABCDEFabcdef0-9]{6}");

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TagUtil.class);

    private Map<TagType, List<Pattern>> patterns = new HashMap<>();

    public TagUtil() {
        patterns.put(TagType.GENERIC, new ArrayList<Pattern>());
        patterns.put(TagType.PERSON, new ArrayList<Pattern>());

        patterns.get(TagType.GENERIC).add(Pattern.compile(GENERIC_SIMPLE));
        patterns.get(TagType.GENERIC).add(Pattern.compile(GENERIC_COMPLEX));
        patterns.get(TagType.PERSON).add(Pattern.compile(PERSON_SIMPLE));
        patterns.get(TagType.PERSON).add(Pattern.compile(PERSON_COMPLEX));
    }

    public boolean doesIncludeTags(String plainText) {
        for (TagType type : patterns.keySet()) {
            for (Pattern p : patterns.get(type)) {
                Matcher m = p.matcher(plainText);
                if (m.find()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String htmlize(String source) {
        source = source.replaceAll(GENERIC_SIMPLE, "<a href=\"/tag/$1\"><span class=\"label label-primary\">$1</span></a>");
        source = source.replaceAll(GENERIC_COMPLEX, "<a href=\"/tag/$1\"><span class=\"label label-primary\">$1</span></a>");
        source = source.replaceAll(PERSON_SIMPLE, "<a href=\"/tag/$1\"><span class=\"label label-success\">$1</span></a>");
        source = source.replaceAll(PERSON_COMPLEX, "<a href=\"/tag/$1\"><span class=\"label label-success\">$1</span></a>");
        source = source.replace("@@", "@");
        source = source.replace("##", "#");
        return source;
    }

    public String replaceToHtml(String source) {
        Document doc = Jsoup.parse(source);
        doc.outputSettings().prettyPrint(false);
        final List<TextNode> textNodes = new ArrayList<>();
        NodeTraversor traversor = new NodeTraversor(new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {

            }

            @Override
            public void tail(Node node, int depth) {
                if (node instanceof TextNode) {
                    TextNode text = (TextNode) node;
                    if (doesIncludeTags(text.text())) {
                        String replacedText = htmlize(text.text());
                        text.after(replacedText);
                        textNodes.add(text);
                    }
                }
            }
        });
        traversor.traverse(doc);
        for (TextNode node : textNodes) {
            node.remove();
        }
        return doc.select("body").html();
    }

    public Set<Tag> getTags(String text) {
        //remove html tags
        text = Pattern.compile("<style>.*</style>",Pattern.DOTALL).matcher(text).replaceAll("");
        text = text.replaceAll("\\<.*?>", "");
        System.out.println(text);
        Set<Tag> tags = new HashSet<>();
        for (TagType type : patterns.keySet()) {
            for (Pattern p : patterns.get(type)) {
                Matcher m = p.matcher(text);
                while (m.find()) {
                    String match = m.group(1);
                    match = match.replaceAll("&nbsp;", " ");
                    if (match.endsWith(";")) {
                        match = match.substring(0, match.length() - 1);
                    }
                    match = match.trim();
                    boolean realTag = true;
                    if (TagType.GENERIC.equals(type) && match.length() == 6 && HEX_CODE.matcher(match).find()) {
                        try {
                            Integer.parseInt(match, 16);
                            realTag = false;
                        } catch (NumberFormatException ex) {
                            //non parsable => it's a real tag
                        }
                    }
                    if (realTag) {
                        Tag t = new Tag();
                        t.setName(match);
                        t.setType(type);
                        tags.add(t);
                    }
                }
            }

        }
        return tags;
    }

    public void updateTags(EntityManager entityManager, EntityWithTag entityWithTags, Set<Tag> newTags) {

        try {

            List<Tag> existingTags = new ArrayList(entityWithTags.getTags());
            Set<String> existingTagNames = new HashSet<>();
            Set<String> newTagNames = new HashSet<>();
            for (Tag tag : existingTags) {
                existingTagNames.add(tag.getName());
            }
            for (Tag tag : newTags) {
                newTagNames.add(tag.getName());
            }

            //check new newTagNames
            for (Tag newTag : newTags) {
                if (!existingTagNames.contains(newTag.getName())) {
                    Tag tagEntity = null;
                    try {
                        tagEntity = entityManager.createNamedQuery("tag.byName", Tag.class).setParameter("name", newTag.getName()).getSingleResult();
                    } catch (NoResultException ex) {
                        tagEntity = new Tag();
                        tagEntity.setName(newTag.getName());
                        tagEntity.setType(newTag.getType());
                        entityManager.persist(tagEntity);
                        entityManager.flush();
                    }
                    //maintain both side
                    tagEntity.getTaggedTexts().add((hu.radio.tilos.model.TextContent) entityWithTags);
                    entityWithTags.getTags().add(tagEntity);
                }
            }


            //check removed newTagNames
            for (Tag oldTag : existingTags) {
                if (!newTagNames.contains(oldTag.getName())) {
                    //registered but the new text doesn't contain it
                    oldTag.getTaggedTexts().remove(entityWithTags);
                    entityWithTags.getTags().remove(oldTag);
                }
            }

        } catch (Exception ex) {
            LOG.error("Can't create tag for entityWithTags " + entityWithTags, ex);
        }

    }

}
