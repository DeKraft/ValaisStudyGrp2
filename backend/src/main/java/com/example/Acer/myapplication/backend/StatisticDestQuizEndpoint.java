package com.example.Acer.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "statisticDestQuizApi",
        version = "v1",
        resource = "statisticDestQuiz",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Acer.example.com",
                ownerName = "backend.myapplication.Acer.example.com",
                packagePath = ""
        )
)
public class StatisticDestQuizEndpoint {

    private static final Logger logger = Logger.getLogger(StatisticDestQuizEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(StatisticDestQuiz.class);
    }

    /**
     * Returns the {@link StatisticDestQuiz} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code StatisticDestQuiz} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "statisticDestQuiz/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public StatisticDestQuiz get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting StatisticDestQuiz with ID: " + id);
        StatisticDestQuiz statisticDestQuiz = ofy().load().type(StatisticDestQuiz.class).id(id).now();
        if (statisticDestQuiz == null) {
            throw new NotFoundException("Could not find StatisticDestQuiz with ID: " + id);
        }
        return statisticDestQuiz;
    }

    /**
     * Inserts a new {@code StatisticDestQuiz}.
     */
    @ApiMethod(
            name = "insert",
            path = "statisticDestQuiz",
            httpMethod = ApiMethod.HttpMethod.POST)
    public StatisticDestQuiz insert(StatisticDestQuiz statisticDestQuiz) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that statisticDestQuiz.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(statisticDestQuiz).now();
        logger.info("Created StatisticDestQuiz with ID: " + statisticDestQuiz.getId());

        return ofy().load().entity(statisticDestQuiz).now();
    }

    /**
     * Updates an existing {@code StatisticDestQuiz}.
     *
     * @param id                the ID of the entity to be updated
     * @param statisticDestQuiz the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code StatisticDestQuiz}
     */
    @ApiMethod(
            name = "update",
            path = "statisticDestQuiz/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public StatisticDestQuiz update(@Named("id") Long id, StatisticDestQuiz statisticDestQuiz) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(statisticDestQuiz).now();
        logger.info("Updated StatisticDestQuiz: " + statisticDestQuiz);
        return ofy().load().entity(statisticDestQuiz).now();
    }

    /**
     * Deletes the specified {@code StatisticDestQuiz}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code StatisticDestQuiz}
     */
    @ApiMethod(
            name = "remove",
            path = "statisticDestQuiz/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(StatisticDestQuiz.class).id(id).now();
        logger.info("Deleted StatisticDestQuiz with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "statisticDestQuiz",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<StatisticDestQuiz> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<StatisticDestQuiz> query = ofy().load().type(StatisticDestQuiz.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<StatisticDestQuiz> queryIterator = query.iterator();
        List<StatisticDestQuiz> statisticDestQuizList = new ArrayList<StatisticDestQuiz>(limit);
        while (queryIterator.hasNext()) {
            statisticDestQuizList.add(queryIterator.next());
        }
        return CollectionResponse.<StatisticDestQuiz>builder().setItems(statisticDestQuizList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(StatisticDestQuiz.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find StatisticDestQuiz with ID: " + id);
        }
    }
}