package org.example.service;
import  org.example.entity.Baking_requests_Entity;
import org.example.repository.Baking_requests_Repository;
import org.example.repository.Users_Repository;
import org.example.repository.Recipes_Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Baking_requests_Service
{
    private final Baking_requests_Repository bakingRepository;
    private final Users_Repository usersRepository;
    private final Recipes_Repository recipesRepository;

    public Baking_requests_Service(Baking_requests_Repository bakingRepository,
                                   Users_Repository usersRepository,
                                   Recipes_Repository recipesRepository)
    {
        this.bakingRepository = bakingRepository;
        this.usersRepository = usersRepository;
        this.recipesRepository = recipesRepository;
    }

    public List<Baking_requests_Entity> getAll()
    {
        return bakingRepository.findAll();
    }

    public Baking_requests_Entity getById(int id)
    {
        return bakingRepository.findById(id);
    }

    public Baking_requests_Entity create(Baking_requests_Entity request)
    {
        if (request.getBakingRequestsQuantity() == null )
        {
            return null;
        }
        if (request.getBakingRequestsUserId() == null )
        {
            return null;
        }
        if (request.getBakingRequestsRecipesId() == null)
        {
            return null;
        }
        request.setBakingRequestsBatchId(null);
        request.setBakingRequestsStatus("New");
        return bakingRepository.create(request);
    }

    public Baking_requests_Entity update(int id, Baking_requests_Entity data)
    {
        Baking_requests_Entity request = bakingRepository.findById(id);
        if (request == null)
        {
            return null;
        }
        if (data.getBakingRequestsQuantity() != null)
        {
            if (data.getBakingRequestsQuantity() <= 0)
            {
                return null;
            }
            request.setBakingRequestsQuantity(data.getBakingRequestsQuantity());
        }
        if (data.getBakingRequestsUserId() != null)
        {
            if (usersRepository.findById(data.getBakingRequestsUserId()) == null)
            {
                return null;
            }
            request.setBakingRequestsUserId(data.getBakingRequestsUserId());
        }
        if (data.getBakingRequestsRecipesId() != null)
        {
            if (recipesRepository.findById(data.getBakingRequestsRecipesId()) == null)
            {
                return null;
            }
            request.setBakingRequestsRecipesId(data.getBakingRequestsRecipesId());
        }
        return bakingRepository.update(request);
    }

    public Baking_requests_Entity delete(int id)
    {
        return bakingRepository.deleteById(id);
    }
}