package asyncTasks;

import android.database.Cursor;

/**
 * Created by mordreth on 10/2/15.
 */
public interface AsyncResponse {
    void processFinish(Cursor responseCursor);
}
