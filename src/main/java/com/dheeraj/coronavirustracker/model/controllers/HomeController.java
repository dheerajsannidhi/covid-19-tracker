package com.dheeraj.coronavirustracker.model.controllers;

import com.dheeraj.coronavirustracker.model.CoronaModel;
import com.dheeraj.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String Home(Model model){
        List<CoronaModel> allStats = coronaVirusDataService.getCoronamodel();
        int totalReportedCases= allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        int totalDiffcases= allStats.stream().mapToInt(stat -> stat.getDiffCases()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalDiffcases",totalDiffcases);
        return "home";
    }
}
