from math import sin, cos, pi, asin, acos

currentX = 0
currentY = 0


def set(x, y):
    global currentX, currentY
    currentX = x
    currentY = y


def shift(x, y):
    global currentX, currentY
    currentX += x
    currentY += y


def bulb(id):
    print "{0} ({1:.2f}, {2:.2f}) R {3} {4} {5}".format(id, currentX, currentY, 200 + id, 300 + id, 400 + id)


def bulbAbs(id, x, y):
    global currentX, currentY
    currentX = x
    currentY = y
    bulb(id)


def bulbRel(id, x, y):
    global currentX, currentY
    currentX += x
    currentY += y
    bulb(id)


def startAlu(closed=True):
    print "ALU" if closed else "ALUOPEN",


def alu(x, y):
    print "({0:.3f}, {1:.3f});".format(x, y),


def aluAbs(x, y):
    global currentX, currentY
    currentX = x
    currentY = y
    alu(currentX, currentY)


def aluRel(x, y):
    global currentX, currentY
    currentX += x
    currentY += y
    alu(currentX, currentY)


def endAlu():
    print


def fullAlu(points):
    startAlu()
    for point in points:
        aluAbs(*point)
    endAlu()


def fullAluRel(start, points):
    startAlu()
    aluAbs(*start)
    for point in points:
        aluRel(*point)
    endAlu()


def arc(centerX, centerY, radius, segments, fromAngle, toAngle):
    for i in xrange(segments + 1):
        angle = fromAngle + (toAngle - fromAngle) * i / segments
        x = centerX + radius * cos(angle)
        y = centerY + radius * sin(angle)
        aluAbs(x, y)


def rightArc(centerX, centerY, radius, segments, height):
    angle = asin(height / 2 / radius)
    arc(centerX, centerY, radius, segments, -angle, angle)


def leftArc(centerX, centerY, radius, segments, height):
    angle = asin(height / 2 / radius)
    arc(centerX, centerY, radius, segments, pi - angle, pi + angle)


def repeatedLetter(shift):
    basePoints = [(2.29, 0.00), (2.18, 0.10), (2.08, 0.22), (1.98, 0.37), (1.98, 1.65), (2.08, 1.80), (2.18, 1.92), (2.29, 2.02), (2.25, 1.77), (2.22, 1.52), (2.21, 1.27), (2.20, 1.01), (2.21, 0.75), (2.22, 0.50), (2.25, 0.25)]
    fullAlu([(p[0] + shift, p[1]) for p in basePoints])
    startAlu()
    rightArc(1.70 + shift, 1.01, 1.23, 16, 2.02)
    leftArc(2.29 + 5.15 + shift, 1.01, 5.15, 8, 2.02)
    endAlu()
    startAlu()
    rightArc(1.70 + shift, 1.01, 1.13, 16, 1.69)
    leftArc(2.29 + 5.15 + shift, 1.01, 5.05, 8, 1.69)
    endAlu()
    print


print "OFFSET 0 2"
print "SCALE 1 -1"
print

for i in xrange(6):
    bulbAbs(i, 0.34 if i % 2 == 0 else 0.44, 0.28 * (i + 1))
bulbAbs(6, 0.14, 1.50)
for i in xrange(7, 11):
    bulbAbs(i, 0.64, 0.38 * (i - 6))
bulbAbs(11, 0.69, 1.92)
print

print "ALU (0.25, 0.00); (0.25, 1.52); (0.06, 1.40); (0.00, 1.48); (0.52, 1.84); (0.52, 0.00);"
print "ALU (0.59, 0.00); (0.59, 1.90); (0.79, 2.05); (0.85, 1.97); (0.69, 1.85); (0.69, 0.00);"
print

print "ALU (0.73, 0.00); (0.96, 0.68); (1.19, 0.00); (1.10, 0.00); (1.02, 0.19); (0.90, 0.19); (0.82, 0.00);"
print "ALU (1.25, 0.00); (0.98, 0.77); (1.07, 1.04); (1.43, 0.00);"
print

print "ALU (1.48, 0.42); (1.48, 0.62); (1.73, 0.62); (1.73, 0.42);"
print "ALU (1.48, 0.68); (1.48, 0.78); (1.73, 0.78); (1.73, 0.68);"
print

repeatedLetter(0)

fullAluRel((3.09, 0.00), [
    ( 0.00,  0.63),
    (-0.10,  0.00),
    ( 0.00,  0.10),
    ( 0.10,  0.00),
    ( 0.00,  0.30),
    ( 0.16,  0.00),
    ( 0.00, -0.30),
    ( 0.06,  0.00),
    ( 0.00,  0.30),
    ( 0.33,  0.00),
    ( 0.00, -0.10),
    (-0.23,  0.00),
    ( 0.00, -0.20),
    ( 0.10,  0.00),
    ( 0.00, -0.10),
    (-0.10,  0.00),
    ( 0.00, -0.53),
    ( 0.23,  0.00),
    ( 0.00, -0.10),
    (-0.33,  0.00),
    ( 0.00,  0.63),
    (-0.06,  0.00),
    ( 0.00, -0.63),
])

repeatedLetter(2.27)

startAlu()
aluAbs( 5.32,  0.30)
aluRel( 0.00,  1.06)
aluRel( 0.10,  0.00)
aluRel( 0.00, -1.06)
arc(5.62, 0.30, 0.20, 16, pi, 2 * pi)
aluAbs( 5.82,  0.30)
aluRel( 0.00,  1.06)
aluRel( 0.10,  0.00)
aluRel( 0.00, -1.06)
arc(5.62, 0.30, 0.30, 16, 2 * pi, pi)
endAlu()

startAlu()
aluAbs( 5.47,  0.30)
aluRel( 0.00,  1.06)
aluRel( 0.15,  0.00)
aluRel( 0.00, -1.21)
arc(5.62, 0.30, 0.15, 8, 3 * pi / 2, pi)
endAlu()

fullAlu([(6.12, 0.32), (6.04, 2.02), (6.17, 2.02), (6.22, 0.32)])
fullAlu([(6.28, 0.32), (6.23, 2.02), (6.43, 2.02), (6.38, 0.32)])
startAlu()
arc(6.25, 0.14, 0.14, 32, 0, 2 * pi * 31 / 32)
endAlu()

print "ALU (3.74, 0.42); (3.74, 0.62); (3.99, 0.62); (3.99, 0.42);"
print "ALU (3.74, 0.68); (3.74, 0.78); (3.99, 0.78); (3.99, 0.68);"
