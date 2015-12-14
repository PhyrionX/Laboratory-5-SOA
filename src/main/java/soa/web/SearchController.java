package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class SearchController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q, @RequestParam(value = "max", required = false) Integer max) {
        //Necesitamos 2 cabeceras unas lo que queremos buscar y otra el máximo de tweets que queremos recuperar
        Map<String, Object> headers = new HashMap<String, Object>();
        //Consulta de búsqueda
        headers.put("CamelTwitterKeywords", q);
        //Límite
        //Si no hay límite recupera 10 cómo máximo
        headers.put("CamelTwitterCount", max);
        return producerTemplate.requestBodyAndHeaders("direct:search", "", headers);

    }
}