package com.projet.forum.Services.UserInfoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.forum.Repositories.UserInfoRepository;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Entities.UserInfoEntity;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired

    private final UserInfoRepository repository;
    private final UserRepository u_repository;

    public UserInfoServiceImpl(UserInfoRepository uir, UserRepository ur){
        this.repository = uir;
        this.u_repository = ur;
    }

    @Override public Map<String, Status> displayTextInfo(Long id){
        
        UserEntity user = u_repository.findById(id).orElseThrow();

        if(user.isArchived()){
            Map<String, Status> user_text = new HashMap<>();
            user_text.put("Deleted user", null);
            return user_text;
        }
        else{

            Map<String, Status> user_text = new HashMap<>();
            user_text.put(user.getUser_info().getLogin(), user.getUser_info().getStatus());
            return user_text;
        }
    }

    @Override public Map<Map<String, Status>, byte[]> displayInfo(Long id){

        UserEntity user = u_repository.findById(id).orElseThrow();

        if(user.isArchived()){
            Map<Map<String, Status>, byte[]> a_user_display = new HashMap<>() {{
                put(displayTextInfo(id), null);
            }};
            return a_user_display;
        }
        else{

             Map<Map<String, Status>, byte[]> user_display = new HashMap<>() {{
                put(displayTextInfo(id), user.getUser_info().getProfile_picture());
            }};

            return user_display;
        }
    }

    @Override public List<?> showInfoOnProfileChart(Long id){

        UserInfoEntity user = repository.findById(id).orElse(null);

        if(user != null){

            List<Object> dispatch = new ArrayList<>(Arrays.asList(
                user.getLogin(),
                user.getProfile_picture(),
                user.getAge(),
                user.getBio(),
                user.getBadges(),
                user.getStatus()));

            return dispatch;
        }

        return null;
    }
}
