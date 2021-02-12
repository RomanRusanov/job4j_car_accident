package ru.job4j.accident.control;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 12.02.2021
 * email roman9628@gmail.com
 */
@Controller
public class AccidentControl {

    private final AccidentMem accidents;
    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());
    private static final Marker MARKER = MarkerFactory.getMarker("Controller");

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        LOG.info(MARKER, "AccidentControl save accident {}", accident);
        accidents.addToStore(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update() {

        return "accident/update";
    }
}