package vn.fis.logfile.vinasoy.service;

import vn.fis.logfile.vinasoy.model.Share;

import java.util.List;

public interface ShareService {
    List<Share> findAll();
    List<Share> findAllshareforme(String foruser);
    List<Share> findAllsharebyme(String bylogin);
    Share create (Share share);

    List<Share> delete (Long id);

}
