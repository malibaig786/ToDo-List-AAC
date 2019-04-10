package com.example.android.todoapp.temp;

public class NextDay {

    // Wednesday, March 21, 2019
    // 1. Having a crash while get single task from TaskViewModel                         Done
    // 2. Update if the Task id edited                                                    Done
    // 3. Change background color of priority in item_task
    // 4. ViewModelFactory

    /* Monday, March 25, 2019 */
    // 3. Change background color of priority in item_task                                Done
    // 4. ViewModelFactory                                                                Done
    // 5. Wrote down the question have in mind.                                           Done
    // 6. Compare the code from course with your implementation

    // Tuesday, March 26, 2019
    // 6. Compare the code from course with your implementation                          Done
    // 7. After removing item from list is the update the adapter? if so it will         Done
    // update adapter list two time one after removal and one when liveData trigger
    // update of list.
    // 8. Find out what is extra and why it is important.                                Done
    // 9. Implement it if it is important.                                               Done
    // 10. List and recheck the issues you think LiveData approach have.                 Done
    // 11. Check if the intent from previous activity will be there after recreation of  Done
    // activity.

    /* Wednesday, March 27, 2019*/
    // List out important code                                                           Done
    // Extract it out                                                                    Done

    /* Thursday, March 28, 2019*/
    // Analysis and document current understanding and knowledge of AAC
    // Study words app , color notes and notes_codingFlow

    // GIT
    // Push the code on gitHub
    // Make a list of repository for common code navigation
    // Summaries what you have learned
    // skim a book
    // Gather knowledge from jump started git.

    // INFO
    // Even if we do not add the remove method of adapter still the swiped item
    // is deleted as we are listening to the whole list with liveData and when
    // the item is removed by swiping the LiveData pass the updated list to the
    // adapter.

    // I am not liking the liveData why of updating the list in the adapter as
    // whole list is updated every time.

    // AsyncTask can cause leak of memory. Make it static.(study)


    // EXTRA-FEATURES
    // 1. Can restore the deleted item.
    // 2. Can also delete from detail list
    // 3. Animation for delete and restore operation
    // 4. Test for checking the functionality


}
