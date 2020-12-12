package com.something.my.trial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class TrialController {

    @GetMapping("/task")
    public RedirectView task(){
        return new RedirectView("https://www.taskade.com/new");
    }

    @GetMapping("/mindmap")
    public RedirectView mindMap(){
        return new RedirectView("https://www.taskade.com/new/brainstorming?as=mindmap");
    }

    @GetMapping("/chart")
    public RedirectView chart(){
        return new RedirectView("https://live.amcharts.com/new");
    }

    @GetMapping("/note")
    public RedirectView note(){
        return new RedirectView("https://quicknote.io/");
    }

    @GetMapping("/videocall")
    public RedirectView videoCall(){
        return new RedirectView("https://gotalk.to/" + UUID.randomUUID().toString());
    }

    @GetMapping("/videocall/{id}")
    public RedirectView videoCall(
            @PathVariable(name = "id") String chatRoomId
    ){
        return new RedirectView("https://gotalk.to/" + chatRoomId);
    }

    @GetMapping("/meeting")
    public RedirectView meeting(){
        return new RedirectView("https://doodle.com/create");
    }

    @GetMapping("/poll")
    public RedirectView poll(){
        return new RedirectView("https://fast-poll.com/new");
    }

    @GetMapping("/banner")
    public RedirectView banner(){
        return new RedirectView("https://canva.new");
    }

    @GetMapping("/board")
    public RedirectView board(){
        return new RedirectView("https://board.new");
    }

    @GetMapping("/html")
    public RedirectView html(){
        return new RedirectView("https://codesandbox.io/s/");
    }

    @GetMapping("/sheet")
    public RedirectView sheet(){
        return new RedirectView("https://docs.google.com/spreadsheets");
    }

    @GetMapping("/py")
    public RedirectView py(){
        return new RedirectView("https://repl.it/repls/BountifulNeatPackagedsoftware#main.py");
    }

}
