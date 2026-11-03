package ek.osnb.demo.conferences.conference;

import ek.osnb.demo.conferences.common.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
class ConferenceService implements ConferenceApi {

    private final ConferenceRepository conferences;

    ConferenceService(ConferenceRepository conferences) {
        this.conferences = conferences;
    }

    List<ConferenceResponse> search(String search) {
        String text = (search == null || search.isBlank()) ? null : search.trim();

        return conferences
                .search(text)
                .stream()
                .map(ConferenceResponse::from)
                .toList();
    }

    ConferenceResponse findById(Long id) {
        return ConferenceResponse.from(findConference(id));
    }

    @Override
    public ConferenceInfo getConference(Long conferenceId) {
        return ConferenceInfo.from(findConference(conferenceId));
    }

    @Override
    public List<ConferenceInfo> findAll() {
        return conferences.findAll()
                .stream()
                .map(ConferenceInfo::from)
                .toList();
    }

    private Conference findConference(Long id) {
        return conferences.findById(id)
                .orElseThrow(() -> new NotFoundException("Conference not found: " + id));
    }
}
