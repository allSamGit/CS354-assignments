#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

% Your code goes here.

starttime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute == Nminute, sethour(Ohour, Hour), sethour(Ominute, Minute)).
starttime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute @> Nminute, sethour(Ohour, Hour), sethour(Ominute, Minute)).
starttime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute @< Nminute, sethour(Ohour, Hour), sethour(Ominute, Nminute)).
starttime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, _, _)) :- (Hour @> Nhour, sethour(Ohour, Hour), sethour(Ominute, Minute)).
starttime(time(Ohour, Ominute, _), time(Hour, _, _), time(Nhour, Nminute, _)) :- (Hour @< Nhour, sethour(Ohour, Nhour), sethour(Ominute, Nminute)).
																						 
endtime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute == Nminute, sethour(Ohour, Hour), sethour(Ominute, Minute)).
endtime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute @> Nminute, sethour(Ohour, Hour), sethour(Ominute, Nminute)).
endtime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, Nminute, _)) :- (Hour == Nhour, Minute @< Nminute, sethour(Ohour, Hour), sethour(Ominute, Minute)).
endtime(time(Ohour, Ominute, _), time(Hour, _, _), time(Nhour, Nminute, _)) :- (Hour @> Nhour, sethour(Ohour, Nhour), sethour(Ominute, Nminute)).
endtime(time(Ohour, Ominute, _), time(Hour, Minute, _), time(Nhour, _, _)) :- (Hour @< Nhour, sethour(Ohour, Hour), sethour(Ominute, Minute)).

timelength(slot(time(Shour, Sminute, Speriod), time(Ehour, Eminute, Eperiod))) :- ((Shour == Ehour, Sminute @< Eminute); (Shour @< Ehour), Speriod == Eperiod).

periodmax(time(_, _, Operiod), time(_, _, Period), time(_, _, Nperiod)) :- maxperiod(Operiod, Period, Nperiod).

maxperiod(Period, Period, Nperiod) :-  Period == pm, Nperiod == am.
maxperiod(Nperiod, Period, Nperiod) :-  Nperiod == pm, Period == am.
maxperiod(Period, Period, Nperiod) :- Period == Nperiod.

minperiod(Period, Period, Nperiod) :- Period == am, Nperiod == pm.
minperiod(Nperiod, Period, Nperiod) :- Nperiod == am, Period == pm.
minperiod(Nperiod, Period, Nperiod) :- Period == Nperiod.

gettime(slot(Overstart, Overend), slot(Start, End), slot(Nextstart, Nextend)) :- starttime(Overstart, Start, Nextstart), endtime(Overend, End, Nextend),
										 periodmax(Overstart, Start, Nextstart), periodmax(Overend, End, Nextend).
sethour(Hour, Hour). 

findtime(Slot, Slot, []).

findtime(Slot, Availability, [Head|Tail]) :- free(Head, Next),gettime(Overlap, Availability, Next),timelength(Overlap),findtime(Slot, Overlap, Tail). 

findtimes(Slot, [Head|Tail]) :- free(Head, Firstavailability), findtime(Slot, Firstavailability, Tail). 

meet(Slot, People) :- write(People), findtimes(Slot, People).

people([ann,bob,carla]).
onepeople([ann,bob,dave]).
twopeople([ann, bob]).
threepeople([carla, bob]).


main :- people(People), findall(Slot, meet(Slot, People), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, tests, halt.

:- initialization(main).


tests :- one, two, three.

% Test cases

one :- onepeople(People), findall(Slot, meet(Slot, People), Slots),
uniq(Slots, Uniq),write(Uniq), nl.

two :- twopeople(People), findall(Slot, meet(Slot, People), Slots),
uniq(Slots, Uniq),write(Uniq), nl.

three :- threepeople(People), findall(Slot, meet(Slot, People), Slots),
uniq(Slots, Uniq),write(Uniq), nl.
