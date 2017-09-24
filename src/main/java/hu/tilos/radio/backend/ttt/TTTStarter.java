package hu.tilos.radio.backend.ttt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class TTTStarter {

    @Autowired
    TTTService tttService;


    @RequestMapping("/api/v1/ttt/business")
    public Object[] home() {
        return tttService.getAllBusiness();
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1/ttt/business/{id}/photo.jpg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("id") String id) {
        return tttService.getImage(id);

    }

}
