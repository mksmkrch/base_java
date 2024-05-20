package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int count = 0;

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume r) {
        int position = checkResume(r.getUuid());
        if (position < 0) {
            storage[count] = r;
            count++;
        } else if (position > 0) {
            System.out.println("Resume with UUID: " + r.getUuid() + " already present");
        } else if (count > storage.length) {
            System.out.println("Storage is full.");
        }
    }

     public Resume get(String uuid) {
        int position = checkResume(uuid);
        if (position < 0) {
            System.out.println(notExistsResume(uuid));
            return null;
        }
        return storage[position];
    }

    public void delete(String uuid) {
        int position = checkResume(uuid);
        if (position < 0) {
            System.out.println(notExistsResume(uuid));
        } else {
            storage[position] = storage[count - 1];
            storage[count - 1] = null;
            count--;
        }
    }

    public void update(Resume r){
        int position = checkResume(r.getUuid());
        if (position < 0) {
            System.out.println(notExistsResume(r.getUuid()));
        } else {
            storage[position] = r;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }

    private int checkResume(String uuid) {
        for (int i = 0; i <= count; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
    
    private String notExistsResume(String uuid) {
        return ("No resume with UUID: " + uuid);
    }
        
}
