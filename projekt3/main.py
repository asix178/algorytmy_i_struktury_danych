from dataclasses import dataclass


@dataclass(frozen=True)
class Line:
    content: str
    type: str


def build_lcs_matrix(text1: list[str], text2: list[str]):
    n = len(text1)
    m = len(text2)

    lcs = [[None for _ in range(m + 1)]
           for _ in range(n + 1)]

    for i in range(0, n + 1):
        for j in range(0, m + 1):
            if i == 0 or j == 0:
                lcs[i][j] = 0
            elif text1[i - 1] == text2[j - 1]:
                lcs[i][j] = 1 + lcs[i - 1][j - 1]
            else:
                lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1])

    return lcs


def diff(text1: list[str], text2: list[str]) -> list[Line]:
    lcs = build_lcs_matrix(text1, text2)
    results = []

    i = len(text1)
    j = len(text2)

    while i != 0 or j != 0:
        if i == 0:
            results.append(Line(text2[j - 1], "+"))
            j -= 1
        elif j == 0:
            results.append(Line(text1[i - 1], "-"))
            i -= 1
        elif text1[i - 1] == text2[j - 1]:
            results.append(Line(text1[i - 1], "="))
            i -= 1
            j -= 1
        elif lcs[i - 1][j] <= lcs[i][j - 1]:
            results.append(Line(text2[j - 1], "+"))
            j -= 1
        else:
            results.append(Line(text1[i - 1], "-"))
            i -= 1

    return list(reversed(results))


def read_lines_from_file(path: str) -> list[str]:
    with open(path, 'r') as f:
        return [line for line in f.read().splitlines()]


def view(lines1: list[str], lines2: list[str]) -> None:
    left = list()
    right = list()
    left_len = 0
    right_len = 0
    diff_result = diff(lines1, lines2)
    for line in diff_result:
        if line.type == "+":
            right.append(line)
            if len(line.content) > right_len:
                right_len = len(line.content)
        elif line.type == "-":
            left.append(line)
            if len(line.content) > left_len:
                left_len = len(line.content)
        elif line.type == "=":
            left.append(line)
            right.append(line)
            if len(line.content) > right_len:
                right_len = len(line.content)
            if len(line.content) > left_len:
                left_len = len(line.content)

    for index in range(0, len(left)):
        left_content = str(left[index].content)
        right_content = str(right[index].content)
        while len(left_content) < left_len:
            left_content += " "
        while len(right_content) < right_len:
            right_content += " "
        print(f"{left_content} {left[index].type} {right_content} {right[index].type}")


def main():
    file1 = "t1.txt"
    file2 = "t2.txt"

    lines1 = read_lines_from_file(file1)
    lines2 = read_lines_from_file(file2)

    view(lines1, lines2)


if __name__ == '__main__':
    main()
