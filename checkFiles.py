import sys

def compare_files(file1_path, file2_path):
    with open(file1_path, 'r') as file1, open(file2_path, 'r') as file2:
        # Read the contents of each file into a list of lines
        file1_lines = file1.readlines()
        file2_lines = file2.readlines()

        # Compare the lines in each file
        if len(file1_lines) != len(file2_lines):
            # If the files have different number of lines, they are not the same
            print("length")
            return False
        
        for i, line in enumerate(file1_lines):
            if line.strip() != file2_lines[i].strip():
                # If the lines are not the same, the files are not the same
                print(line.strip())
                print(file2_lines[i].strip())
                return False

    # If all lines are the same, the files are the same
    return True

if __name__ == "__main__":
    file1 = "ExpectedOutput/StressTest4Priority-0.9M-0.99E2E.ra"
    file2 = "Sprint3DebugOutputFiles/StressTest4Priority-0.9M-0.99E2E.ra"
    print(compare_files(file1, file2))