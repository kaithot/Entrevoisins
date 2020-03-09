package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** TODO 11 Unit Tests
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();

    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(2);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }


    @Test
    public void addNeighbourFavorite() {

        Neighbour favoriteToAdd = service.getNeighbours().get(1);
        service.addFavorite(favoriteToAdd);
        assertTrue(service.getFavoritesNeighbours().contains(favoriteToAdd));
    }

    @Test
    public void deleteNeighbourFavorite() {
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        service.getFavoritesNeighbours().add(favoriteToAdd);
        Neighbour favoriteToDelete = service.getNeighbours().get(0);
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavoritesNeighbours().contains(favoriteToDelete));
    }
}
