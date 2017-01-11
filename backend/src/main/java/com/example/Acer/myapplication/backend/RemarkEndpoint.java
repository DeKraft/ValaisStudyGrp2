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
        name = "remarkApi",
        version = "v1",
        resource = "remark",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Acer.example.com",
                ownerName = "backend.myapplication.Acer.example.com",
                packagePath = ""
        )
)
public class RemarkEndpoint {

    private static final Logger logger = Logger.getLogger(RemarkEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Remark.class);
    }

    /**
     * Returns the {@link Remark} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Remark} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "remark/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Remark get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting Remark with ID: " + id);
        Remark remark = ofy().load().type(Remark.class).id(id).now();
        if (remark == null) {
            throw new NotFoundException("Could not find Remark with ID: " + id);
        }
        return remark;
    }

    /**
     * Inserts a new {@code Remark}.
     */
    @ApiMethod(
            name = "insert",
            path = "remark",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Remark insert(Remark remark) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that remark.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(remark).now();
        logger.info("Created Remark with ID: " + remark.getId());

        return ofy().load().entity(remark).now();
    }

    /**
     * Updates an existing {@code Remark}.
     *
     * @param id     the ID of the entity to be updated
     * @param remark the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Remark}
     */
    @ApiMethod(
            name = "update",
            path = "remark/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Remark update(@Named("id") Long id, Remark remark) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(remark).now();
        logger.info("Updated Remark: " + remark);
        return ofy().load().entity(remark).now();
    }

    /**
     * Deletes the specified {@code Remark}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Remark}
     */
    @ApiMethod(
            name = "remove",
            path = "remark/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Remark.class).id(id).now();
        logger.info("Deleted Remark with ID: " + id);
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
            path = "remark",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Remark> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Remark> query = ofy().load().type(Remark.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Remark> queryIterator = query.iterator();
        List<Remark> remarkList = new ArrayList<Remark>(limit);
        while (queryIterator.hasNext()) {
            remarkList.add(queryIterator.next());
        }
        return CollectionResponse.<Remark>builder().setItems(remarkList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(Remark.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Remark with ID: " + id);
        }
    }
}