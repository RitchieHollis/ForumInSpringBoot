package com.projet.forum.Services.UserInfoServices;

import org.springframework.stereotype.Service;
import com.projet.forum.Repositories.UserInfoRepository;
import com.projet.forum.Entities.UserInfoEntity;
import com.projet.forum.Entities.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    
    private UserInfoRepository repository;

    @Override public Map<Map<String, Status>, byte[]> displayInfo(Long id){

        UserInfoEntity user = repository.findById(id).orElse(null);

        if(user != null){

            Map<String, Status> user_text = new HashMap<>();
            user_text.put(user.getLogin(), user.getStatus());

            Map<Map<String, Status>, byte[]> user_display = new HashMap<>() {{
                put(user_text, user.getProfile_picture());
            }};

            return user_display;
        }
        else return null;
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