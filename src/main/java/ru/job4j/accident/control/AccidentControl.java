package ru.job4j.accident.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 12.02.2021
 * email roman9628@gmail.com
 */
@Controller
public class AccidentControl {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());
    private static final Marker MARKER = MarkerFactory.getMarker("Controller");
    private AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        this.service.saveAccident(accident, typeId);
        LOG.info(MARKER, "AccidentControl save accident {}", accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.getAccidentById(id));
        return "accident/update";
    }

    @ModelAttribute
    public void addAttributeForCreateAndUpdateRequest(Model model) {
        model.addAttribute("types", service.getAllAccidentTypes());
    }
}