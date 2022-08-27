package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;

import vn.fis.logfile.vinasoy.model.Suggestion;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.repository.SuggestionRepository;
import vn.fis.logfile.vinasoy.service.SuggestionService;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    @Override
    public List<SuggestionSproDto> findAll(String username) {
        return suggestionRepository.suggestion(username);
    }

    @Override
    public Suggestion create(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion findById(Long id, String u) {
        return suggestionRepository.findidAttachmentofSuggestion(u,id);
    }

    @Override
    public void delete(Long id) {
        suggestionRepository.deleteById(id);
    }

}
