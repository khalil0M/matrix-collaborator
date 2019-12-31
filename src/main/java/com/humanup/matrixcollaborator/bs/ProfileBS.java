package com.humanup.matrixcollaborator.bs;
import com.humanup.matrixcollaborator.vo.ProfileVO;

import java.util.List;

public interface ProfileBS {
    boolean createProfile(ProfileVO profile);
    ProfileVO findProfileByTitle(String profileTitle);
    List<ProfileVO> findListProfile();
    List<ProfileVO> findListProfilesByTitle(String title);
}
