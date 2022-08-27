package vn.fis.logfile.vinasoy.service;

import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.model.Suggestion;

import java.util.List;

public interface SuggestionService {
    List<SuggestionSproDto> findAll(String username);

    Suggestion create (Suggestion suggestion);
    Suggestion findById(Long id, String u);
    void delete (Long id);

}
