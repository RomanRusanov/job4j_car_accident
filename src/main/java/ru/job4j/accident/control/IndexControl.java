package ru.job4j.accident.control;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.AppContext;
import ru.job4j.accident.WebInit;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 * Обработкой вида будет заниматься контроллер.
 * Это обычный класс с особыми аннотациями.
 * Spring сканирует проект и регистрирует этот контроллер.
 * Когда на Dispacher приходит запрос, он ищет подходящий контроллер.
 * Метод index возвращает имя вида без расширения.
 */
@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("all_accidents", getAllAccidents());
        return "index";
    }

    public Collection<Accident> getAllAccidents() {
        AnnotationConfigApplicationContext context = AppContext.getInstance().getAppContext();
        AccidentMem storage = context.getBean(AccidentMem.class);
        return storage.getAllAccidents();
    }
}