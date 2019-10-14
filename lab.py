import sys
import time
import numpy as np
import random as rd
import pandas as pd

swaps = 0
tc = 0

def insertionSort(inlis):
    """Insertion Sort implementation."""
    global swaps, tc

    # Copy the list so we don't affect the original.
    lis = inlis.copy()

    # Swap each element down the list until it's in its proper place.
    for i in range(len(lis)):
        for j in range(i, 0, -1):
            tc += 1
            if lis[j] < lis[j-1]:
                lis[j-1], lis[j] = lis[j], lis[j-1]
                swaps += 1
            else:
                break
    return lis

def mergeSort(lis):
    """Merge sort implementation."""

    # End the recursion when list size is 1.
    if len(lis) > 1:
        # Divide list into two sublists.
        lis1 = lis[:len(lis)//2]
        lis2 = lis[len(lis)//2:]

        # Merge the two sub-lists so that the resulting list is in order.
        lis = merge(mergeSort(lis1), mergeSort(lis2))
    return lis

def merge(lis1, lis2):
    """Merge function for use in merge sort."""
    global tc

    # Create empty list for the result of the merge.
    lis = np.zeros(len(lis1) + len(lis2), dtype=int)
    i = j = k = 0

    # Merge the list until one of them is used up.
    while i < len(lis1) and j < len(lis2):
        tc += 1
        if lis1[i] < lis2[j]:
            lis[k] = lis1[i]
            i += 1
            k += 1
        else:
            lis[k] = lis2[j]
            j += 1
            k += 1

    # Add the remainder to the new list.
    lis[k:] = lis1[i:].copy() if len(lis1[i:])>0 else lis2[j:].copy()
    return lis

def generateArrays(lower, upper, step):
    """
    Generate a dictionary of random, ascending, and descending arrays with the
    given range of sizes.
    """

    arrays = {"random":np.empty((upper-lower)//step, dtype=object),
              "ascending":np.empty((upper-lower)//step, dtype=object),
              "descending":np.empty((upper-lower)//step, dtype=object)}

    print("Generating Arrays...")
    for num, i in enumerate(range(lower, upper, step)):
        print(f"at {num}")
        arrays["random"][num] = np.random.randint(10000, size=i)
        arrays["ascending"][num] = np.arange(1, i+1)
        arrays["descending"][num] = np.arange(i,0,-1)
    print("Done generating.")
    return arrays

def measure():
    """Measure the perfomance of the insertion and merge sort implementations."""
    global swaps, tc

    num_arrays = 999
    # Create a dictionary to hold the measurements.
    data = {"insertion":{"time":np.zeros(num_arrays),
                         "swaps":np.zeros(num_arrays),
                         "tc":np.zeros(num_arrays)},
            "merge":{"time":np.zeros(num_arrays),
                     "tc":np.zeros(num_arrays)}}

    # Initialise a dataframe for usage later.
    results = pd.DataFrame(columns=["Sort Type", "Time", "Time Complexity",
                                    "Swaps", "Array Type"])

    # Get the arrays used for testing.
    arrays = generateArrays(1000, 1000000, 1000)
    # arrays = generateArrays(1000, 1001, 1)

    print("Starting measurements")
    # Get measurements for each type of array.
    for arr_type in ["random", "ascending", "descending"]:
        print(f"Currently measuring {arr_type} arrays.")
        for i, array in enumerate(arrays[arr_type]):
            print(f"Array number {i}")
            # Reset the counts
            swaps = tc = 0

            # Measure the time before and after and record te number of swaps
            # and the time complexity for insertion sort.
            pre_time = time.time()
            insertionSort(array)
            data["insertion"]["time"][i] = time.time()-pre_time
            data["insertion"]["swaps"][i] = swaps
            data["insertion"]["tc"][i] = tc

            # Reset the time complexity count. Merge sort doesn't use swaps.
            tc = 0

            # Measure the time and the time complexity for merge sort.
            pre_time = time.time()
            mergeSort(array)
            data["merge"]["time"][i] = time.time()-pre_time
            data["merge"]["tc"][i] = tc
        
        # Create a dataframe for the insertion sort measurements.
        f1 = pd.DataFrame()
        f1["Time"] = data["insertion"]["time"]
        f1["Time Complexity"] = data["insertion"]["tc"]
        f1["Swaps"] = data["insertion"]["swaps"]
        f1["Sort Type"] = "insertion"
        f1["Array Type"] = arr_type

        # Create a dataframe for the merge sort measurements.
        f2 = pd.DataFrame(columns=["Swaps"])
        f2["Time"] = data["merge"]["time"]
        f2["Time Complexity"] = data["merge"]["tc"]
        f2["Sort Type"] = "merge"
        f2["Array Type"] = arr_type

        # Add the two dataframes to the results dataframe.
        results = pd.concat([results, f1, f2], ignore_index=True)
    # Return the results.
    return results[["Sort Type", "Time", "Time Complexity", "Swaps", "Array Type"]]


if __name__ == "__main__":
    rd.seed(42)
    # print(randList(5))
    # arr = np.asarray([1,3,5,2,3,21,2,3,5,7,3,2])
    # print(arr)

    # print(insertionSort(arr))
    # print(arr)
    # print(mergeSort(arr))
    # print(generateArrays(1, 5, 1))
    print(measure())
