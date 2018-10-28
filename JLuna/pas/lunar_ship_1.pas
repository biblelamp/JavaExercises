// from https://pmk.arbinada.com/ru/node/710
program lunar_ship_1;

{$mode objfpc}{$H+}

uses
  Math;

var
  h_i,  // altitude - vertical coordinate, m - current and next
  x_i,  // longitude - horizontal coordinate, m
  v_i,  // horizontal velocity, m/sec
  u_i,  // rate of climb, vertical velocity, m/sec
  mf_i, // mass (amount) of the fuel, kg
  ms,          // mass of the ship without fuel, kg
  a_lim,       // acceleration limit, m/sec2
  // In-flight maneuver:
  d_mf, // fuel consumption, kg
  t,    // time, sec
  ac,   // angle of climb, degrees; is always 0 for vertical flight mode

  g,  // acceleration of gravity, m/sec2
  nf, // nozzle flow, m/sec
  ls, // life support, sec
  a,  // current acceleration
  q   // fuel consumption by time
    : real;
  FlightMode          : cardinal = 1;
  WillReverseOfControl: boolean = false;
  WillQuitProgram     : boolean = false;


procedure DoStep;
var
  v_i1, u_i1: real;
begin
  repeat
    v_i1 := v_i;
    u_i1 := u_i;
    v_i := v_i + a * t * sin(ac);
    x_i := x_i + (v_i1 + v_i) / 2 * t;
    u_i := u_i + (a * cos(ac) - g) * t;
    h_i := h_i + (u_i1 + u_i) / 2 * t;
    mf_i := mf_i - q * t;

    ls := ls - t;

    if mf_i < 0 then
      t := mf_i / q;
  until mf_i >= 0;
end;

procedure OutInfo;
begin
  writeln('-------------------------------------------');
  writeln('Altitude             : ', h_i:10:2, ' m');
  if FlightMode = 2 then begin
    writeln('Longitude            : ', x_i:10:2, ' m');
    writeln('Velocity (horizontal): ', v_i:10:2, ' m/sec');
  end;
  writeln('Velocity (vertical)  : ', u_i:10:2, ' m/sec');
  writeln('Fuel                 : ', mf_i:10:0, ' kg');
  writeln('Life support         : ', ls:10:0, ' sec');
  writeln('-------------------------------------------');
end;

procedure EnterManeuver(
  out WillReverseOfControl: boolean;
  out WillQuitProgram     : boolean);
begin
  repeat
    writeln('Enter in-flight maneuver');
    write('Fuel consumption, kg: '); readln(d_mf);
    WillQuitProgram := d_mf < 0;
    if WillQuitProgram then
      exit;
    write('Time, sec           : '); readln(t);
    if FlightMode <> 2 then
      ac := 0
    else begin
      write('Climb angle, °      : '); readln(ac);
      ac := DegToRad(ac);
    end;
  until t <> 0;

  WillReverseOfControl := t < 0;
  t := abs(t);
end;

procedure CalcAcceleration(WillReverseOfControl: boolean);
begin
  q := d_mf / t;
  a := q * nf / (ms + mf_i);
  if WillReverseOfControl then begin
    a := -a;
    WillReverseOfControl := false;
  end;
end;

begin
  writeln('Lunar ship simple simulator');
  writeln('(c) 1985 "Lunolet-1", "Lunilet-2" by Mikhail PUKHOV, USSR');
  writeln('(c) 2011 Serguei TARASSOV, pmk.arbinada.com');
  writeln;
  writeln('Enter simulator mode:');
  writeln('   1 - "Lunolet-1" - vertical flight only');
  writeln('   2 - "Lunolet-2" - vertical and horizontal flight (2-dimensional)');
  readln(FlightMode);
  writeln('Enter negative fuel consumption value to quit the program');

  g := 1.62; // 1,62 m/sec2 for Lune
  ms := 2250;
  nf := 3660;
  a := 0;
  a_lim := 3 * 9.81; // "3g"
  v_i := 0;
  u_i := 0;
  h_i := 0;
  x_i := 0;
  mf_i := 400;
  ls := 3600;

  repeat
    if h_i < 0 then begin
      t := 2 * h_i /
        (sqrt(sqr(u_i) + 2 * h_i * (g - a * cos(ac))) - u_i);
    end
    else begin
      if (h_i = 0) or ((a < a_lim) and (mf_i <> 0)) then begin
        OutInfo;
        EnterManeuver(WillReverseOfControl, WillQuitProgram);
        if WillQuitProgram then
          break;
        CalcAcceleration(WillReverseOfControl);
        writeln('Acceleration: ', a:8:2, ' m/sec2');
      end
      else begin
        d_mf := 0;
        if not (a < a_lim) then begin
          writeln('!!! Acceleration exceeded');
          t := a - a_lim;
          writeln('Waiting ', t:5:1, ' sec');
        end;
        if mf_i <= 0 then begin
          writeln('!!! Fuel exceeded');
          t := nf;
        end;
        CalcAcceleration(false);
      end;
    end;

    DoStep;
  until WillQuitProgram;
end.
