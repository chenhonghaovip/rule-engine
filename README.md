1、名词定义
  规则因子：本质上是一个变量
  规则因子分组：对规则因子进行同类归并，比如简历分组、职位分组等
  原子条件：由一个左值、比较符、右值构成的boolean类型的最小粒度的规则
  规则：原子条件和逻辑运算符（&& 、||、！）符号组成的规则条件（condition）以及规则行为（action）
  规则组：规则间的执行逻辑（顺序执行、选择执行等），支持SPI业务定义调度策略
  规则包：由一系列规则及规则间调度逻辑（规则组）构成
  <img width="1096" alt="截屏2024-12-06 15 14 00" src="https://github.com/user-attachments/assets/febc5ae3-3092-454a-8b12-0ec41e1efc6d">


2、架构图
  希望能够做到规则内因子级别的编排能力以及自定义规则间的调度能力
<img width="971" alt="截屏2024-12-06 15 12 24" src="https://github.com/user-attachments/assets/20ea7773-aa98-4650-bbcb-f8d02ba30c8a">
