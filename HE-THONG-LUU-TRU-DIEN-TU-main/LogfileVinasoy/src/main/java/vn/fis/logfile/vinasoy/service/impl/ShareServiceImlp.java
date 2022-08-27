package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.model.Share;
import vn.fis.logfile.vinasoy.repository.ShareRepository;
import vn.fis.logfile.vinasoy.service.ShareService;

import java.util.List;

@Service
public class ShareServiceImlp implements ShareService {
    @Autowired
    private ShareRepository shareRepository;

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @Override
    public List<Share> findAllsharebyme(String byuser) {
        return shareRepository.sharebyme(byuser);
    }

    @Override
    public List<Share> findAllshareforme(String forlogin) {
        return shareRepository.shareforme(forlogin);
    }

    @Override
    public Share create(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public List<Share> delete(Long id) {
        shareRepository.deleteById(id);
        return shareRepository.findAll();
    }
}
