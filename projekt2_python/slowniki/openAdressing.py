class OpenAddressingDict:
    def __init__(self, size_param: int = 10, threshold_param: float = 0.7):
        if size_param < 1:
            raise ValueError("size must be greater than zero")
        self.size = size_param
        if threshold_param < 0.1 or threshold_param > 0.9:
            raise ValueError("threshold must be between 0.1 and 0.9")
        self.threshold = threshold_param
        self.count = 0
        self.table = [None] * size_param

    def hash_key(self, key_param: int) -> int:
        return key_param % self.size

    def rehash_key(self, new_size: int) -> None:
        new_table = [None] * new_size
        for item in self.table:
            if item is not None:
                index = self.hash_key(item[0])
                while new_table[index] is not None:
                    index = (index + 1) % new_size
                new_table[index] = item
        self.table = new_table
        self.size = new_size

    def insert(self, key_param: int, value_param) -> None:
        if self.count / self.size >= self.threshold:
            self.rehash_key(self.size * 2)
        index = self.hash_key(key_param)
        while self.table[index] is not None and self.table[index][0] != key_param:
            index = (index + 1) % self.size
        if self.table[index] is None:
            self.table[index] = (key_param, value_param)
            self.count += 1
        else:
            self.table[index] = (key_param, value_param)

    def find(self, key_param: int):
        index = self.hash_key(key_param)
        while self.table[index] is not None:
            if self.table[index] is not None and self.table[index][0] == key_param:
                return self.table[index][1]
            index = (index + 1) % self.size
        return None

    def delete(self, key_param: int) -> None:
        index = self.hash_key(key_param)
        while self.table[index] is not None:
            if self.table[index][0] == key_param:
                self.table[index] = None
                self.count -= 1
                return
            index = (index + 1) % self.size
        if self.size > 10 and self.count / self.size <= 0.2:
            self.rehash_key(self.size // 2)
