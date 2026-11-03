package ek.osnb.demo.conferences.talk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class TalkController {

    private final TalkService service;

    TalkController(TalkService service) {
        this.service = service;
    }

    @GetMapping("/api/conferences/{conferenceId}/talks")
    List<TalkResponse> findByConference(@PathVariable Long conferenceId) {
        return service.findByConference(conferenceId);
    }

    @GetMapping("/api/conferences{conferenceId}/speakers")
    List<String> speakers(@PathVariable Long conferenceId) {

        return service.speakers(conferenceId);
    }
}
