package vn.fis.logfile.vinasoy.service;

import vn.fis.logfile.vinasoy.model.Star;

import java.util.List;

public interface StarService {
    Star addstar (Star star);
    List<Star> ListStar( String u);

}
