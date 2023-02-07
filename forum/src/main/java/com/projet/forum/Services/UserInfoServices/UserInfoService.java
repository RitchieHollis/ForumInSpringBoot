package com.projet.forum.Services.UserInfoServices;

import java.util.List;
import java.util.Map;

import com.projet.forum.Entities.Status;

public interface UserInfoService {
    
    public abstract Map<Map<String, Status>, byte[]> displayInfo(Long id);

    public abstract List<?> showInfoOnProfileChart(Long id); //this or simply get functions separately

    public abstract Map<String, Status> displayTextInfo(Long id);
}
