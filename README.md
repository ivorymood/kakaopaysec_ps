# kakaopaysec_ps

## Q1

편의점 알바를 하던 죠르디는 알파벳 문자로 이루어진 상품코드가 너무 길어 별도의 규칙을 만들어 인코딩하려고 합니다. 죠르디는 문자열 안에 연속적으로 반복되는 문자를 "반복되는횟수 [문자]"형태로 인코딩할 계획입니다. 예를들어 aaaz는 3[a]z로 인코딩되고 accccaccccacccc는 a4[c]a4[c]a4[c]로 1회 인코딩 되고 다시3[a4[c]]로 인코딩 됩니다. 인코딩에 너무 집중한 죠르디는 인코딩한 문자열을 다시 원래 문자열로 만드는 디코딩 프로그램 작성을 깜빡했습니다. 죠르디 대신 인코딩된 문자열이 들어왔을 때, 디코딩된 문자열을 반환하는 프로그램을 작성해 주세요. 

규칙

- 입력되는 문자열은 숫자, 문자, 괄호로만 이루어져 있습니다. 
- 숫자는 양의 정수이며, 문자에는 숫자가 포함되지 않습니다. 예를 들어 3a, 2[4]와 같은 입력은 존재하지 않습니다.
- 입력되는 문자열의 길이는 L은 0 < L < 128 입니다.

</br>

### 테스트케이스

|      입력      |                 출력                 |
| :------------: | :----------------------------------: |
|     3[a]z      |                 aaaz                 |
|    3[a4[c]]    |           accccaccccacccc            |
| 3[a10[c]]f2[z] | accccccccccaccccccccccaccccccccccfzz |

</br>

</br>

## Q2

가로가 x, 세로가 y이고 각 칸에는 숫자 s가 적혀 있는 말판이 있습니다.    네오와 프로도는 자기 말을 각각 왼쪽 상단과 오른쪽 상단에 두고 게임을 시작하려고 합니다.    말은 자기의 위치에서 아래 3칸(왼쪽아래, 아래, 오른쪽아래)중에 한곳으로만 이동할 수 있고,    두 말이 이동한 위치에 적힌 숫자의 총합이 게임의 점수가 됩니다.    각 칸에 점수가 적힌 말판이 주어질 때, 네오와 프로도가 얻을 수 있는 가장 높은 점수를 구하는 코드를 작성해주세요.    

 규칙    

- 말판의 가로 x, 세로 y의 범위는 3 < x, y < 50,    
- 각 칸의 점수 s의 범위는 -100 < s < 100    
- 입력은 2차원 배열로 주어집니다.
- 말은 말판 밖으로 나갈 수 없습니다.
- 두 말이 같은 칸으로 이동할 수 있지만, 점수는 한명에게만 주어집니다.

입력처리

- 첫번째 입력 : 가로 x 값
- 두번째 입력 : 세로 y 값
- 세번째 입력 : 맵 데이터 , 로 구분

</br>

### 테스트케이스

|                             입력                             | 출력 |
| :----------------------------------------------------------: | :--: |
|            3<br />4<br />3,1,1,2,5,1,1,5,5,2,1,1             |  24  |
| 7<br />5<br />    1,0,0,0,0,0,1,2,0,0,0,0,3,0,2,0,9,0,0,0,0,0,3,0,5,4,0,0,1,0,2,3,0,0,6 |  28  |
| 4<br />4<br />    -10,-10,-10,-10,-20,-20,-20,-20,-30,-30,-30,-30,-40,-40,-40,-40 | -130 |

