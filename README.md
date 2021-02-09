# BlumBlumShub_generator

Program that shows how BlumBlumShub generator works.

Its goal is to generate pseudorandom stream keys.

The main difficulty of the algorithm usage is to calculate the Blum number, which is the result of the multiplication of two large prime numbers that are congruent with 3 mod 4. The safety and quality of generator mostly depend on these two numbers.

# Algorithm:

1. get the Blum number: N = p * q, where p and q are congruent with 3 mod 4;
2. Randomly generate prime number x, that is relatively prime with N;
3. Designate primary value of the generator: x = x^2 mod N;
4. Repeat the 3rd step in the loop: x(i+1) = x(i) ^ 2 mod N;

The least significant bit of the result is the i-th bit of the key.

The algorithm generates 20 000-bit BBS key and checks the corecctness with 4 implemented FIPS 240-2 tests.


Supervisor: Anna Grocholewska-Czuryło Ph.D.
