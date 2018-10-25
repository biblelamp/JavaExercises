
function Lunolet1(writeRecord) {
    this.writeRecord_ = writeRecord;

    // flight constants
    this.gravityAccel    = 1.62    ; // at Moon surface
    this.dryMass         = 2150    ; // lunolet and pilot
    this.exhaustSpeed    = 3660    ; // kerosene and oxygene
    this.accelLimit      = 3 * 9.81; // 3G, where G is gravity acceleration at Earth surface
    this.timeFactor      = -1      ; // count time up
    this.startHeight     = 0       ;
    this.startSpeed      = 0       ;
    this.startFuelMass   = 0       ;
    this.startFlightTime = 0       ;

    // flight variables
    this.height     = 0;
    this.speed      = 0;
    this.fuelMass   = 0;
    this.flightTime = 0;
}

Lunolet1.eps_ = 1e-5;

Lunolet1.prototype.start = function() {
    this.height     = this.startHeight    ;
    this.speed      = this.startSpeed     ;
    this.fuelMass   = this.startFuelMass  ;
    this.flightTime = this.startFlightTime;

    this.write_('старт');
};

Lunolet1.prototype.simulate_ = function(accelSign, mass, duration) {
    if (duration > 0 && this.height >= -Lunolet1.eps_) {
        if (this.fuelMass > 0) {
            if (mass > this.fuelMass) {
                duration *= this.fuelMass / mass;
                mass = this.fuelMass;
            }

            var consumption = mass / duration;
            var accel =
                accelSign * consumption * this.exhaustSpeed /
                (this.dryMass + this.fuelMass)
            ;

            this.fly_('', mass, duration, accel);
            if (this.height > 0) {
                var unconscious = Math.abs(accel) - this.accelLimit
                if (unconscious > 0) {
                    this.fly_('перегрузка', 0, unconscious, 0);
                }
            }
        }
        if (this.fuelMass <= 0 && this.height > Lunolet1.eps_) {
            this.land_('бак пуст', 0, 0);
        }
    }
};

Lunolet1.prototype.fly_ = function(event, mass, duration, accel) {
    var height = this.heightAfter_(duration, accel);
    if (height >= -Lunolet1.eps_) {
        this.advance_(event, mass, duration, accel);
    } else {
        this.land_(event, mass / duration, accel);
    }
};

Lunolet1.prototype.land_ = function(event, consumption, accel) {
    var duration = 0;
    var fullAccel = accel - this.gravityAccel;
    if (fullAccel == 0) {
        if (this.speed != 0) {
            var t = - this.height / this.speed;
            if (t > 0) {
                duration = t;
            }
        }
    } else {
        var d = this.speed * this.speed - 2 * fullAccel * this.height;
        if (d >= 0) {
            var sqrtD = Math.sqrt(d);
            var t1 = (-this.speed - sqrtD) / fullAccel;
            var t2 = (-this.speed + sqrtD) / fullAccel;
            if (t1 > 0) {
                duration = t1;
            }
            if (t2 > 0 && t2 < duration) {
                duration = t2;
            }
        }
    }
    if (duration > 0) {
        this.advance_(event, duration * consumption, duration, accel);
    }
};

Lunolet1.prototype.advance_ = function(event, mass, duration, accel) {
    var oldHeight = this.height;
    this.fuelMass -= mass;
    this.flightTime -= duration * this.timeFactor;
    this.height = this.heightAfter_(duration, accel);
    this.speed  = this.speedAfter_ (duration, accel);

    this.write_(event, mass, (accel >= 0) ? duration : -duration, accel);
    if (this.height < Lunolet1.eps_ && Lunolet1.eps_ < oldHeight) {
        this.height = 0;
        this.speed  = 0;
        this.writeRecord_({'event': 'посадка'});
    }
};

Lunolet1.prototype.speedAfter_ = function(duration, accel) {
    return this.speed + duration * (accel - this.gravityAccel);
};

Lunolet1.prototype.heightAfter_ = function(duration, accel) {
    var avgSpeed = (this.speed + this.speedAfter_(duration, accel)) / 2;
    return this.height + duration * avgSpeed;
};

Lunolet1.prototype.write_ = function(event, mass, duration, accel) {
    this.writeRecord_({
        'event'       : event          ,
        'mass'        : mass           ,
        'duration'    : duration       ,
        'height'      : this.height    ,
        'speed'       : this.speed     ,
        'acceleration': accel          ,
        'fuel'        : this.fuelMass  ,
        'time'        : this.flightTime
    });
};

Lunolet1.commands = [
// [[args patterns], args processor]
    [['\\d+(\\.\\d*)?', '-?\\d+(\\.\\d*)?'], function(args) {
        var mass     = parseFloat(args[0]);
        var duration = parseFloat(args[1]);
        this.simulator.simulate_((duration < 0) ? -1 : 1, mass, Math.abs(duration));
    }]
];

Lunolet1.vars = {
// 'var': ['simulator field', arg pattern, title]
    'g': ['gravityAccel'   ,   '\\d+(\\.\\d*)?', 'м/с<sup>2</sup> - ускорение силы тяжести'],
    'M': ['dryMass'        ,   '\\d+(\\.\\d*)?', 'кг - сухая масса'                        ],
    'c': ['exhaustSpeed'   ,   '\\d+(\\.\\d*)?', 'м/с - скорость истечения'                ],
    'a': ['accelLimit'     ,   '\\d+(\\.\\d*)?', 'м/с<sup>2</sup> - предельное ускорение'  ],
    'v': ['startSpeed'     , '-?\\d+(\\.\\d*)?', 'м/с - стартовая скорость'                ],
    'h': ['startHeight'    ,   '\\d+(\\.\\d*)?', 'м - стартовая высота'                    ],
    'T': ['startFlightTime', '-?\\d+(\\.\\d*)?', 'с - стартовое время'                     ],
    't': ['timeFactor'     , '-?\\d+(\\.\\d*)?', '- коэффициент времени'                   ],
    'm': ['startFuelMass'  ,   '\\d+(\\.\\d*)?', 'кг - масса топлива'                      ]
};

Lunolet1.params = [
// ['param', 'title', align, title padding, value padding, accuracy]
    ['event'       , 'событие'        , 'center', 20, 20   ],
    ['mass'        , 'расход'         , 'right' , 10, 20, 3],
    ['duration'    , 'время'          , 'right' , 10, 20, 3],
    ['speed'       , 'скорость'       , 'right' , 10, 20, 3],
    ['height'      , 'высота'         , 'right' , 10, 20, 3],
    ['acceleration', 'ускорение'      , 'right' , 10, 20, 3],
    ['fuel'        , 'топливо'        , 'right' , 10, 20, 3],
    ['time'        , 'время<br>полета', 'right' , 10, 20, 3]
];

var Simulator = Lunolet1;
