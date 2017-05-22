package com.example.dns.calculator;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<Element> array_el;

    public Calculator(String str)
    {
        array_el = new ArrayList<>();
        char []example = str.toCharArray();
        String per = "";
        for(int i=0;i<example.length;i++)
        {//создаем цикл для создания массива значений для переменных и для знаков
            if (    example[i] != '(' && example[i] != ')' &&
                    example[i] != '*' && example[i] != '/' &&
                    example[i] != '+' && example[i] != '-' &&
                    i!=example.length-1)
            {
                per+=example[i];
            }
            else
            {
                if(example[i]=='('||example[i]==')')
                {
                    if(i==example.length-1)
                    {
                        if(per=="")
                        {
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                        else
                        {
                            array_el.add(new Element(per,true));
                            per = "";
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                    }
                    else
                    {
                        if(per=="")
                        {
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                        else
                        {
                            array_el.add(new Element(per,true));
                            per = "";
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                    }
                }
                else
                {
                    if(i==example.length-1)
                    {
                        per+=example[i];
                        array_el.add(new Element(per,true));
                    }
                    else
                    {
                        if(per=="")
                        {
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                        else
                        {
                            array_el.add(new Element(per,true));
                            per = "";
                            array_el.add(new Element(String.valueOf(example[i]),false));
                        }
                    }
                }
            }
        }
    }

    private class Element
    {
        private String el;//елемент
        private boolean num;//проверка на число(true число, false знак)
        public Element(String el,boolean num)
        {
            this.num = num;
            this.el = el;
        }

        public String getEl() {
            return el;
        }

        public boolean isNum() {
            return num;
        }

        @Override
        public String toString() {
            return "(" + el + "," + num + ")";
        }
    }

    private int SearchBracket(int x)
    {
        int k=0;
        for(int i=x;i<array_el.size();i++)
        {
            if(array_el.get(i).getEl().equals("("))
            {
                k++;
            }
            if(array_el.get(i).getEl().equals(")"))
            {
                k--;
            }
            if(k==0)
            {
                return i;
            }
        }
        return 0;
    }

    public double Calculate() throws DivideNullException,OperationErrorException
    {
        if(array_el.get(0).getEl().equals("-"))
        {
            if(array_el.get(1).isNum())
            {
                double d = -1 * Double.valueOf(array_el.get(1).getEl());
                array_el.set(0,new Element(String.valueOf(d),true));
                array_el.remove(1);
            }
            else
            {
                array_el.set(0,new Element("-1",true));
                array_el.add(1,new Element("*",false));
            }
        }
        for(int i = 0;i <array_el.size() ;i++)
        {
            int k = 0;
            if(array_el.get(i).getEl().equals("("))
            {
                k = SearchBracket(i);
                Calculate(i+1,k);
                array_el.remove(i+2);
                array_el.remove(i);
            }
        }
        for(int i = 0;i <array_el.size() ;i++)
        {
            if(!array_el.get(i).isNum())
            {
                double a = 0;
                double b = 0;
                if(array_el.get(i-1).isNum()&&array_el.get(i+1).isNum())
                {
                    a = Double.valueOf(array_el.get(i-1).getEl());
                    b = Double.valueOf(array_el.get(i+1).getEl());
                }
                else
                {
                    throw new OperationErrorException("Неправильно набран пример");
                }
                double c = 0.0;
                if(array_el.get(i).getEl().equals("*")||array_el.get(i).getEl().equals("/"))
                {
                    if(array_el.get(i).getEl().equals("*"))
                    {
                        c = a * b;
                    }
                    else
                    {
                        if(b==0) throw new DivideNullException("Деление на 0:",a);
                        else c = a / b;
                    }
                    array_el.set(i,new Element(String.valueOf(c),true));
                    array_el.remove(i+1);
                    array_el.remove(i-1);
                    i--;
                }
            }
        }
        for(int i = 0;i < array_el.size();i++)
        {
            if(!array_el.get(i).isNum())
            {
                double a = 0;
                double b = 0;
                if(array_el.get(i-1).isNum()&&array_el.get(i+1).isNum())
                {
                    a = Double.valueOf(array_el.get(i-1).getEl());
                    b = Double.valueOf(array_el.get(i+1).getEl());
                }
                else
                {
                    throw new OperationErrorException("Неправильно набран пример");
                }
                double c = 0.0;
                if(array_el.get(i).getEl().equals("+")||array_el.get(i).getEl().equals("-"))
                {
                    if(array_el.get(i).getEl().equals("+"))
                    {
                        c = a + b;
                    }
                    else
                    {
                        c = a - b;
                    }
                    array_el.set(i,new Element(String.valueOf(c),true));
                    array_el.remove(i+1);
                    array_el.remove(i-1);
                    i--;
                }
            }
        }
        return Double.valueOf(array_el.get(0).getEl());
    }

    public double Calculate(int begin, int end) throws DivideNullException,OperationErrorException
    {
        int x = begin;
        int y = end;
        if(array_el.get(x).getEl().equals("-"))
        {
            if(array_el.get(x+1).isNum())
            {
                double d = -1 * Double.valueOf(array_el.get(x+1).getEl());
                array_el.set(x,new Element(String.valueOf(d),true));
                array_el.remove(x+1);
                y--;
            }
            else
            {
                array_el.set(x,new Element("-1",true));
                array_el.add(x+1,new Element("*",false));
                y++;
            }
        }
        for(int i = x;i <y ;i++)
        {
            int k = 0;
            if(array_el.get(i).getEl().equals("("))
            {
                k = SearchBracket(i);
                Calculate(i+1,k);
                array_el.remove(i+2);
                array_el.remove(i);
                y-=k-i;
            }
        }
        for(int i = x;i <y ;i++)
        {
            if(!array_el.get(i).isNum())
            {
                double a = 0;
                double b = 0;
                if(array_el.get(i-1).isNum()&&array_el.get(i+1).isNum())
                {
                    a = Double.valueOf(array_el.get(i-1).getEl());
                    b = Double.valueOf(array_el.get(i+1).getEl());
                }
                else
                {
                    throw new OperationErrorException("Неправильно набран пример");
                }
                double c = 0.0;
                if(array_el.get(i).getEl().equals("*")||array_el.get(i).getEl().equals("/"))
                {
                    if(array_el.get(i).getEl().equals("*"))
                    {
                        c = a * b;
                    }
                    else
                    {
                        if(b==0) throw new DivideNullException("Деление на 0:",a);
                        else  c = a / b;
                    }
                    array_el.set(i,new Element(String.valueOf(c),true));
                    array_el.remove(i+1);
                    array_el.remove(i-1);
                    i--;
                    y-=2;
                }
            }
        }
        for(int i = x;i < y;i++)
        {
            if(!array_el.get(i).isNum())
            {
                double a = 0;
                double b = 0;
                if(array_el.get(i-1).isNum()&&array_el.get(i+1).isNum())
                {
                    a = Double.valueOf(array_el.get(i-1).getEl());
                    b = Double.valueOf(array_el.get(i+1).getEl());
                }
                else
                {
                    throw new OperationErrorException("Неправильно набран пример");
                }
                double c = 0.0;
                if(array_el.get(i).getEl().equals("+")||array_el.get(i).getEl().equals("-"))
                {
                    if(array_el.get(i).getEl().equals("+"))
                    {
                        c = a + b;
                    }
                    else
                    {
                        c = a - b;
                    }
                    array_el.set(i,new Element(String.valueOf(c),true));
                    array_el.remove(i+1);
                    array_el.remove(i-1);
                    i--;
                    y-=2;
                }
            }
        }
        return Double.valueOf(array_el.get(x).getEl());
    }
}
