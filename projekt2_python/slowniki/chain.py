class ChainHashDict:
    def __init__(self, size_param: int = 10, threshold_param: float = 0.7):
        if size_param < 1:
            raise ValueError("size must be greater than zero")
        self.size = size_param
        if threshold_param < 0.1 or threshold_param > 0.9:
            raise ValueError("threshold must be between 0.1 and 0.9")
        self.threshold = threshold_param
        self.count = 0
        self.table = [[] for _ in range(size_param)]

    def hash_key(self, key_param: int) -> int:
        return key_param % self.size

    def rehash_key(self, new_size_param: int) -> None:
        new_table = [[] for _ in range(new_size_param)]
        for chain in self.table:
            for pair in chain:
                new_index = self.hash_key(pair[0])  # Haszujemy klucz pary
                new_table[new_index].append(pair)
        self.table = new_table
        self.size = new_size_param

    def insert(self, key_param: int, value_param) -> None:
        if self.count / self.size >= self.threshold:
            self.rehash_key(self.size * 2)
        index = self.hash_key(key_param)
        chain = self.table[index]
        for pair in chain:
            if pair[0] == key_param:  # Jeśli klucz już istnieje, aktualizujemy wartość
                pair[1] = value_param
                return
        chain.append([key_param, value_param])  # Dodajemy nową parę klucz-wartość
        self.count += 1

    def find(self, key_param: int):  # Theta(l-by elementow) - optymalnie Theta(elementy/lancuchy + 1)
        index = self.hash_key(key_param)
        chain = self.table[index]
        for pair in chain:
            if pair[0] == key_param:
                return pair[1]  # Zwracamy wartość dla danego klucza
        return None  # Jeśli klucz nie został znaleziony, zwracamy None

    def delete(self, key_param: int) -> None:
        index = self.hash_key(key_param)
        chain = self.table[index]
        for i, pair in enumerate(chain):
            if pair[0] == key_param:
                del chain[i]  # Usuwamy parę klucz-wartość z łańcucha
                self.count -= 1
                if self.size > 10 and self.count / self.size <= 0.2:
                    self.rehash_key(self.size // 2)
                return
