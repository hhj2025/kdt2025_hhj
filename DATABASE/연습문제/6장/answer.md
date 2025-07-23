## 1. 다음 릴레이션에서 후보키를 찾아보시오.

**HINT** 함수 종속성을 보고 결정자 중 기본키가 되는 것들이 있는지를 찾아낸다.  
예를 들어 R(A, B, C)에 대해 아래에서 성립하는 함수 종속성으로부터  
R의 모든 속성을 결정하는 A → ABC와 같은 함수 종속성을 유도할 수 있다면 A가 R의 키가 된다.

---

### a.
- 릴레이션: R(A, B, C, D)  
- 함수 종속성: A → BC, B → A, A → C, A → D, D → A

  ```
   후보키 : A, B, D
  ```
---

### b.
- 릴레이션: R(A, B, C, D)  
- 함수 종속성: A → B, B → C, AC → D

  ```
   후보키 : A
  ```

---

### c.
- 릴레이션: R(A, B, C, D, E)  
- 함수 종속성: AB → C, CD → E, DE → B

  ```
   후보키 : ACD, ADE
  ```
---

### d.
- 릴레이션: R(A, B, C, D, E)  
- 함수 종속성: AC → E, C → D, D → A

  ```
   후보키 : BC
  ```

---

## 2. 다음은 배송(Shipping) 물품에 대한 릴레이션이다. 물음에 답하시오.

릴레이션:  
Shipping(shipname, shiptype, voyageID, cargo, port, date)  
함수 종속성:
- shipname → shiptype
- voyageID → shipname, cargo
- shipname, date → voyageID, port

### A. 후보키를 찾으시오.

  ```
   후보키 : (shipname, date), (voyageID, date)
  ```

### B. 제2정규형으로 정규화하시오.

  ```
   그림은 ppt
   
   R1(shipname, shiptype)

   R2(shipname, date, voyageID, cargo, port)
  ```

### C. 제3정규형으로 정규화하시오.

  ```
   R1(shipname, shiptype)

   R2(shipname, date, voyageID, port)

   R3(voyageID, cargo)
  ```

### D. BCNF로 정규화하시오.

  ```
   R1(shipname, shiptype)

   R2(shipname, date, voyageID, port)

   R3(voyageID, cargo)
  ```

---

## 3. 다음은 부품과 공급자에 대한 릴레이션 Part(partnumber, description, supplier, suppaddress, price)이다. 물음에 답하시오.

| partnumber | description    | supplier   | suppaddress | price   |
|------------|----------------|------------|-------------|---------|
| 10         | 20 GB Disk     | Seagate    | CA          | 100000  |
| 10         | 20 GB Disk     | IBM        | NY          | 90000   |
| 20         | 256 MB RAM     | Kensington | CA          | 22000   |
| 20         | 256 MB RAM     | IBM        | NY          | 29000   |
| 30         | 256 MB RAM     | Samsung    | Seoul       | 31000   |
| 40         | LCD Monitor    | IBM        | NY          | 210000  |

---

### A. 함수 종속성을 찾아보시오.

  ```
   (partnumber, supplier) → price

   supplier → suppaddress

   partnumber → description
  ```

### B. 릴레이션 Part는 어떤 정규형인가?

  ```
   1정규형
  ```

### C. 다음과 같이 분해했을 때 각각의 릴레이션은 어떤 정규형인가?

- R1(partnumber, description, supplier, price)  
- R2(supplier, suppaddress)

  ```
   R1 = 1정규형

   R2 =  BCNF정규형

  ```
  
---

### D. (C)번의 릴레이션에서 분해가 더 필요한가? 필요하면 분해를 수행하시오.

  ```
   R1(partnumber, supplier, price)

   R2(supplier, suppaddress)

   R3(partnumber, description)
  ```

