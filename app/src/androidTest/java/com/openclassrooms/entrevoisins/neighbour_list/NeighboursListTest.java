
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.SelectViewFavorite;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


/** TODO 10 Instrumentation Tests
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_COUNT_FAV = 0;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {

        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {

        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        //Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }


    @Test
    public void myNeighboursList_shouldShowProfile(){
        // When perform a click on a avatar icon show profile
        onView (allOf(withId (R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1,new SelectViewFavorite()));
        onView (allOf(withId (R.id.neighboursPicture))).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void myNeighboursList_onProfile_shouldShowRightName() {
        int id = 1;
        onView (allOf(withId (R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(id,new SelectViewFavorite()));
        onView(allOf(withId(R.id.neighboursName),isDisplayed())).check(matches(withText(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getName())));


}
    @Test
    public void myNeighboursList_shouldOnlyShowFavs() {

        onView (allOf(withContentDescription("Favorites"),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT_FAV ));
        onView (allOf(withContentDescription("My neighbours"),isDisplayed())).perform(click());
        onView (allOf(withId (R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(2,new SelectViewFavorite()));
        onView (allOf(withId (R.id.fab),isDisplayed())).perform(click());
        onView(allOf(withContentDescription("Navigate up"),isDisplayed())).perform(click());
        onView (allOf(withContentDescription("Favorites"),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT_FAV + 1));

    }

}