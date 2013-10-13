<?php

namespace Radio\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity 
 * @ORM\Table(name="episode")
 * */
class Episode {

    /**
     * @ORM\Id 
     * @ORM\Column(type="integer") 
     * @ORM\GeneratedValue 
     * */
    protected $id;

    /**
     * @ORM\Column(type="datetime") 
     * */
    protected $plannedFrom;

    /**
     * @ORM\Column(type="datetime") 
     * */
    protected $plannedTo;
    /**
     * @ORM\Column(type="datetime") 
     * */
    protected $realFrom;

    /**
     * @ORM\Column(type="datetime") 
     * */
    protected $realTo;

    /**
     * @ORM\ManyToOne(targetEntity = "Show")
     * @ORM\JoinColumn(name = "radioshow_id", referencedColumnName = "id")
     */
    protected $show;
    
    /**
     * So called 'Adadnaplo'
     * 
     * @ORM\OneToOne(targetEntity="TextContent")
     * @ORM\JoinColumn(name="textcontent_id", referencedColumnName="id")
     */
    protected $text;

    public function getId() {
        return $this->id;
    }
    public function getPlannedFrom() {
        return $this->plannedFrom;
    }

    public function getPlannedTo() {
        return $this->plannedTo;
    }

    
    public function getRealFrom() {
        return $this->realFrom;
    }

    public function getRealTo() {
        return $this->realTo;
    }

    public function getShow() {
        return $this->show;
    }
    public function setPlannedFrom($plannedFrom) {
        $this->plannedFrom = $plannedFrom;
    }

    public function setPlannedTo($plannedTo) {
        $this->plannedTo = $plannedTo;
    }

    public function setRealFrom($realFrom) {
        $this->realFrom = $realFrom;
    }

    public function setRealTo($realTo) {
        $this->realTo = $realTo;
    }

    public function setShow($show) {
        $this->show = $show;
    }

    public function getText() {
        return $this->text;
    }

    public function setText($text) {
        $this->text = $text;
    }




    

}

?>