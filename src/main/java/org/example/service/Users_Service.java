package org.example.service;
import  org.example.entity.Users_Entity;
import org.example.entity.Baking_requests_Entity;
import org.example.repository.Users_Repository;
import org.example.repository.Baking_requests_Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Users_Service
{
    private final Users_Repository usersRepository;
    private final Baking_requests_Repository bakingRepository;

    public Users_Service(Users_Repository usersRepository,Baking_requests_Repository bakingRepository) {
        this.usersRepository = usersRepository;
        this.bakingRepository = bakingRepository;
    }

    public List<Users_Entity> getAll()
    {
        return usersRepository.findAll();
    }

    public Users_Entity getById(int id)
    {
        return usersRepository.findById(id);
    }

    public Users_Entity create(Users_Entity user)
    {
        if (user.getUserName()==null)
        {
            return null;
        }

        return usersRepository.create(user);
    }

    public Users_Entity update(int id, Users_Entity data)
    {
        Users_Entity user = usersRepository.findById(id);
        if (user == null)
        {
            return null;
        }
        if (data.getUserName() != null)
        {
            if (data.getUserName().isBlank())
            {
                return null;
            }
            user.setUserName(data.getUserName());
        }
        return usersRepository.update(user);
    }

    public Users_Entity delete(int id)
    {
        Users_Entity deleted = usersRepository.deleteById(id);
        if (deleted == null)
        {
            return null;
        }
        List<Baking_requests_Entity> requests = bakingRepository.findByUserId(id);
        for (int i = 0; i < requests.size(); i++)
        {
            bakingRepository.deleteById(requests.get(i).getBakingRequestsId());
        }
        return deleted;
    }
}