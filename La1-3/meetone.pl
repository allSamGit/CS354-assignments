#!/bin/gprolog --consult-file

:- include('data.pl').

% Your code goes here.

period(Period, Freeperiod) :- (Freeperiod == am, Period == pm).
period(Period, Freeperiod) :- (Freeperiod == Period).

startTime(time(Hour, Minute, Period), time(Freehour, Freeminute, Freeperiod)) :- ((Hour==Freehour, Freeminute=<Minute);
																		(Freehour<Hour)),
																			period(Period, Freeperiod).

endTime(time(Hour, Minute, Period), time(Freehour, Freeminute, Freeperiod)) :- ((Hour==Freehour, Freeminute>=Minute);
																		(Freehour>Hour)),
																			period(Freeperiod, Period).

meetone(Person,slot(Start, End)) :- free(Person, slot(Starttime, Endtime)),
				startTime(Start, Starttime),
				 endTime(End, Endtime).


main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, tests, halt.

:- initialization(main).

tests :- one, two, three, four.

one :- findall(Person, meetone(Person,slot(time(10,0,am),time(10,30,am))),
       People), write(People), nl.

two :- findall(Person, meetone(Person,slot(time(11,30,am),time(2,0,pm))),
	   People), write(People), nl.

three :- findall(Person, meetone(Person,slot(time(9,30,am),time(9,45,am))),
	     People), write(People), nl.

four :- findall(Person, meetone(Person,slot(time(10,0,pm),time(11,45,pm))),
		People), write(People), nl.