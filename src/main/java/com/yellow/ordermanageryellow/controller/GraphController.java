package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.Dto.TopEmploeeyDTO;
import com.yellow.ordermanageryellow.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Graph")

public class GraphController {
    @Autowired
    private GraphService graphService;

    @GetMapping("/topEmploeey")
    public List<TopEmploeeyDTO> topEmploeey() {
        return graphService.topEmployee();
    }
}


