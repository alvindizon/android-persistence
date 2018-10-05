/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.codelab.step3;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.android.persistence.codelab.db.AppDatabase;
import com.example.android.persistence.codelab.db.Book;
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer;

import java.util.List;

/**
 * The BooksBorrowedByUserViewModel class exposes the list of books wrapped in a LiveData object,
 * and is also responsible for creating and populating the database
 */
public class BooksBorrowedByUserViewModel extends AndroidViewModel {

    public final LiveData<List<Book>> books;

    private AppDatabase mDb;

    public BooksBorrowedByUserViewModel(Application application) {
        super(application);
        createDb();

//        retrieve the LiveData object from the DAO and use it to subscribe to changes using the name field
        books = mDb.bookModel().findBooksBorrowedByName("Mike");
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
