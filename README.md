# 使用指南

一款使用反射机制，能够自动为类的属性设置默认值的工具

| License                                        | CodeBeat                                                    | Language                                                     | Build                                                        | Size                                                         | Contributors                                                 |
| ---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![Hex.pm](https://img.shields.io/hexpm/l/plug) | [![codebeat badge](https://codebeat.co/badges/319e72b9-fd7e-4697-88c7-ac17ee8b7e42)](https://codebeat.co/projects/github-com-hongshuboy-setdefaultvalue-master) | ![language java](<https://img.shields.io/badge/java-v1.8-blue>) | ![GitHub release (latest by date)](https://img.shields.io/github/v/release/hongshuboy/setdefaultvalue) | ![GitHub repo size](https://img.shields.io/github/repo-size/hongshuboy/setdefaultvalue) | ![GitHub contributors](https://img.shields.io/github/contributors/hongshuboy/setdefaultvalue) |

- [x] 支持基本数据类型的默认值赋值
- [x] 支持引用数据类型的默认值赋值
- [x] 支持数组的默认值赋值
- [x] 支持目标类继承形态下的默认值赋值
- [x] 支持引用类型` prototype`模式的赋值
- [x] 支持根据类型\字段名的自定义设置
- [x] 支持多种不同形式的扩展
- [x] …

### 适用场景

> ***[本工具原理是通过反射调用Getter和Setter方法进行赋值，因此需要传入的对象有这类方法]***

一个`Java`类型，如`Student`

```java
public class Student {
    private Integer id;
    private Integer age;
    private String name;
    private Integer[] ids;
    private String schoolName;
    private int num;
    private List<String> friends;
    getters and setters ...   
}
```

对于这样一个`Student`对象，如果我们不对它的属性赋值的话，默认会是这样的

```java
Student{id=null, age=null, name='null', ids=null, schoolName='null', num=0, friends=null}
```

很简单的道理，基本类型的`num`有默认值`0`，其他引用类型均为`null`

但通过本工具，我们将可以对这些`null`的字段进行特殊处理，自动放入我们期望的值，达到自定义默认值的效果，当然，还有其他好用的功能，请看下面。

### 上手使用

**1.默认赋值方法**

比如下面这样一个例子

```java
/**
  * 默认赋值方法
  * 最简单的使用方式，一行代码即可让对象的属性获得初始化的默认值
  */
@Test
public void test1() {
    final Student student = new Student();

    //自动赋值
    ValueUtils.setDefaultValue(student);

    System.out.println(student);
}
```

通过`ValueUtils`工具的反射调用后，所有属性都有了它的默认值

> 1.Integer = 0
>
> 2.String = ""
>
> 3.数组 = 空数组
>
> 等等...

```java
Student{id=0, age=0, name='', ids=[], schoolName='', num=0, friends=[]}
```

**2.根据类型自定义默认值**

但有时候我们希望的赋值可能不是上面定义的规则，这时可以使用`Configuration`灵活配置

假如我们希望Integer类型的默认值为`1`而非`0`、`String`的默认值为`hi`、Integer数组的默认值为包含`1`和`2`的数组，我们可以如下设置

```java
/**
  * 自定义默认值
  * 假如我们想对Integer类型设置的默认值是1，那么可以单独进行设置
  */
@Test
public void test2() {
    final Student student = new Student();

    final Configuration configuration = new HashConfiguration();
    configuration.setDefaultConfig(Type.INTEGER, 1);
    configuration.setDefaultConfig(Type.STRING, "hi");
    configuration.setDefaultConfig(Type.INTEGER_ARRAY, new Integer[]{1, 2});
    configuration.setDefaultConfig(Type.LIST, Arrays.asList("tom", "mike"));

    ValueUtils.setDefaultValue(student, configuration);

    System.out.println(student);
}
```

这时`student`的输出就很有意思了

```java
Student{id=1, age=1, name='hi', ids=[1, 2], schoolName='hi', num=0, friends=[tom, mike]}
```

**3.设置忽略字段**

还有一种可能，假如我们想对某些字段跳过，不让工具自动赋默认值，比如{XxxEntity}实体类，因为保存到数据库id自增的关系，我们希望id字段能保持为`null`，不要自动赋值，就可以使用`Configuration`的`setIgnoreFields`来处理

```java
/**
  * 设置忽略字段
  * 1.设置INTEGER类型赋值为1（默认框架赋值为0）
  * 2.自动赋值时，忽略ids和schoolNamE两个字段（可写1至多个字段，自动会忽略大小写进行匹配）
  */
@Test
public void test3() {
    final Student student = new Student();

    final Configuration configuration = new HashConfiguration();
    configuration.setDefaultConfig(Type.INTEGER, 1);
    configuration.setDefaultConfig(Type.STRING, "hi");
    configuration.setDefaultConfig(Type.INTEGER_ARRAY, new Integer[]{1, 2});
    /**
      *  这里虽然设置的是id和schOOlNamE字段，但实际比较的是id和schoolName（会自动忽略大小写）
      *  同时，类中的字段在匹配时，也会被忽略大小写，所以这里可以完全不管大小写，均可匹配到属性
      */
    configuration.setIgnoreFields("id", "schOOlNamE");

    ValueUtils.setDefaultValue(student, configuration);

    System.out.println(student);
}	
```

输出：

```java
Student{id=null, age=1, name='hi', ids=[1, 2], schoolName='null', num=0, friends=[]}
```

如此，我们便可以方便的进行默认值的设置了

**4.根据字段，设置特定的值**

```java
/**
 * 根据字段，设置特定的值
 * 字段名同样忽略大小写
 * - [1.2新增]：支持对基础类型赋值
 */
@Test
public void test4() {
    final Student student = new Student();

    final Configuration configuration = new HashConfiguration();
    //注意此处num的类型是基础类型int 这里同样支持
    configuration.setDefaultValueByFieldName("num", 100);
    configuration.setDefaultValueByFieldName("schOOlNamE", "Harvard University");

    ValueUtils.setDefaultValue(student, configuration);

    System.out.println(student);
}
```

输出：

```java
Student{id=0, age=0, name='', ids=[], schoolName='Harvard University', num=100, friends=[]}
```

**5.支持基本数据类型的默认值赋值**

你可能已经注意到，在`test4`中`num`的类型是`int`，是基本数据类型，它在`Java`语言中默认会是`0`而不是`Null`，这种情况下，依然可以对其设置默认值，但前提是它的值是默认值`0`，若此时`num`的值是`1`，那么就会跳过。

`Java`的基本数据类型在此都支持默认值赋值，为了区分基本的数据类型和基本的引用类型，比如`int`和`Integer`，基本数据类型在`Type`中的命名都是以下划线结尾，比如：

`int` => `Type.INT_`  

`Integer` => `Type.INTEGER`

```java
@Test
public void test5(){
    final Student student = new Student();

    final Configuration configuration = new HashConfiguration();
    //将int类型默认值设置为1
    configuration.setDefaultConfig(Type.INT_, 1);

    ValueUtils.setDefaultValue(student, configuration);

    System.out.println(student);
}
```

输出：

```
Student{id=0, age=0, name='', ids=[], schoolName='', num=1, friends=[], family=[]}
```

**6.集合类型默认使用反射生成新对象**

```java
/**
 * [1.2新增]
 * 集合类型默认使用反射生成新对象，避免后续操作时会互相影响
 */
@Test
public void test6() {
    //1.2新增测试字段List<String> family 和 friends类型一致
    final Student student = new Student();

    ValueUtils.setDefaultValue(student);

    System.out.println(student);

    student.getFriends().add("tom");
    student.getFamily().add("mike");

    //此处可以看到二者互相没有影响，正常使用
    System.out.println(student);
}
```

输出：

```
Student{id=0, age=0, name='', ids=[], schoolName='', num=0, friends=[], family=[]}
Student{id=0, age=0, name='', ids=[], schoolName='', num=0, friends=[tom], family=[mike]}
```

**7.支持用户自定义反射生成新对象**

```java
/**
 * [1.2新增]
 * 若用户自定义的值为Class，则会使用反射来生成，普通对象不做处理，直接使用
 */
@Test
public void test7() {
    final Student student = new Student();

    final Configuration configuration = new HashConfiguration();
    //configuration.setDefaultConfig(Type.LIST, new LinkedList<>());
    configuration.setDefaultConfig(Type.LIST, LinkedList.class);
    ValueUtils.setDefaultValue(student, configuration);

    student.getFriends().add("tom");
    student.getFamily().add("mike");

    System.out.println(student.getFamily().hashCode());
    System.out.println(student.getFriends().hashCode());
    System.out.println(student);
}
```

输出：

```
3351573
115057
Student{id=0, age=0, name='', ids=[], schoolName='', num=0, friends=[tom], family=[mike]}
```

以上所有的代码均在`test/java/`下，可直接运行测试

**8.如何自定义配置**

目前支持的自动赋值类型能够在`com.github.jteam.value.Type`下找到，这是一个枚举类型，目前支持9种基本的引用类型（如`Integer`、`String`等），9种数组类型和10种集合类型（如`List`、`Map`等）

如果你需要支持更多的类型，或者其他规则，可以**实现**`Configuration`接口，默认有一个实现是`HashConfiguration`，如果你只是希望添加几种支持的自动赋值类型，你也可以直接继承`HashConfiguration`类，重写`addConfig()`方法，向`configMap`中添加设置，`Key`是希望匹配的全类名，`Value`是希望对这个类型设置的默认值。

```java
public class TestConfiguration extends HashConfiguration {
    @Override
    protected void addConfig() {
        configMap.put(String.class.getName(), "default string");
    }
}
```

另外一种简单的方式，假如只是少许内容需要定制，比如`LocalDateTime`在`Type`中没有提供，可以直接通过`configuration`实例进行设置

可以通过`configuration`的`Type(Class)`或者`FieldName`进行设置，下面演示的是根据`Type(Class)`设置的方式

```java
public class Senior extends Student{
    private LocalDateTime birthday;
    getters and setters ... 
}
```

```java
@Test
public void test8(){
    Senior senior = new Senior();
    final Configuration configuration = new HashConfiguration();

    configuration.setDefaultConfig(LocalDateTime.class.getName(), LocalDateTime.now());
    ValueUtils.setDefaultValue(senior, configuration);

    System.out.println(senior);
}
```

输出：

```
Senior{birthday=2021-01-23T18:00:51.762}Student{id=0, age=0, name='', ids=[], schoolName='', num=0, friends=[], family=[]}
```

再次提醒：**[本工具原理是通过反射调用Getter和Setter方法进行赋值，因此需要传入的对象有这类方法]**

### 作者

弘树丶

> wangpeng(hongshu)

Email:hongshuboy@gmail.com

### 版权说明 

本项目使用**Apache License 2.0**授权许可，详情请参阅 ***\LICENSE***

*hongshuboy/SetDefaultValue is licensed under the Apache License 2.0,please read LICENSE for more information*

Copyright ©2020 wangpeng(hongshu)
