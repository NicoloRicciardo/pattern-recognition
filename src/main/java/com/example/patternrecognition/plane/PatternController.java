package com.example.patternrecognition.plane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatternController {

    private final PatternService patternService;

    @Autowired
    public PatternController(PatternService patternService) {
        this.patternService = patternService;
    }

    @GetMapping("/space")
    public List<Point> getPointList() {
        return patternService.getPointList();
    }

    @GetMapping("/lines")
    public List<Line> getLineList() {
        return patternService.getLineList();
    }

    @GetMapping("/lines/{num}")
    public List<Line> getLinesWithNPoints(@PathVariable Integer num) {
        return patternService.getLinesWithNPoints(num);
    }

    @PostMapping("/point")
    public String createPoint(@RequestBody Point point) {
        return patternService.createPoint(point);
    }

    @DeleteMapping("/space")
    public String deleteAllPoints() {
        return patternService.deleteAllPoints();
    }
}
