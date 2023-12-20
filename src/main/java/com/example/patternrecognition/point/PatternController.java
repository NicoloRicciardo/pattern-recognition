package com.example.patternrecognition.point;

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
    public List<Point> getPoint() {
        return patternService.getPointList();
    }

    @GetMapping("/lines")
    public List<Line> getLine() {
        return patternService.getLineList();
    }

    @GetMapping("/lines/{num}")
    public List<Line> getAllLine(@PathVariable Integer num) {
        return patternService.getLinesWithNPoints(num);
    }

    @PostMapping("/point")
    public String postPoint(@RequestBody Point point) {
        return patternService.createPoint(point);
    }

    @DeleteMapping("/space")
    public String deleteAllPoints() {
        return patternService.deleteAllPoints();
    }
}
