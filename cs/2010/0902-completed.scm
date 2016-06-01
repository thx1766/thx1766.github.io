;; Exercise 1
(cons 'explosive (cons (cons 'sniffing (cons 'dog '())) '()))
(cons (cons 'explosive (cons 'sniffing '())) (cons 'dog '()))
(cons (cons 'explosive '()) (cons 'sniffing (cons 'dog '())))
(cons (cons (cons 'explosive '()) (cons 'sniffing '())) (cons 'dog '()))

;; Exercise 2
orange
(fries)

;; Exercise 3
rutgers
;no

;; Exercise 4
; no

;; The Little Lisper exercise 1.9
lemons
and
(and (coffee))

;; The Little Lisper exercise 1.10
(car (car (cdr (cdr l))))
(car (cdr (cdr l)))
(car (car (car (cdr (cdr (car l))))))

;; The Little Lisper exercise 2.1
#t
#t
#f

;; The Little Lisper exercise 2.6
(define member-cake?
  (lambda (x)
    (if (null? x)
    #f
    (if (eq? (car x) 'cake)
    #t
    (member-cake? (cdr x))
    ))))

;; The Little Lisper exercise 2.7
; yes
; The functions are almost identical - the only difference is that in the or check, member checks the equality of a and the first part of lat, while member2 checks the second half first.

;; The Little Lisper exercise 2.10
(define member-twice?
  (lambda (a lat)
    (if (null? lat)
        #f
        (if (and (eq? a (car lat)) (member? a (cdr lat)))
            #t
            (member-twice? a (cdr lat))
            ))))

;; The Little Lisper exercise 3.1
(define seconds
  (lambda (l)
    (cond
      ((null? l) '())
      (else (cons (car (cdr (car l)))
                  (seconds (cdr l)
                           ))))))

;; The Little Lisper exercise 3.2
(define dupla
  (lambda (a l)
    (cond
      ((null? l) '())
      (else (cons a (dupla a (cdr l)))))))

;; The Little Lisper exercise 3.3
(define double
  (lambda (a l)
    (cond
      ((null? l) '())
      ((eq? (car l) a) (cons a(cons a(cdr l))))
      (else (cons (car l) (double a (cdr l)))))))

;; The Little Lisper exercise 3.4
(define subst-sauce
  (lambda (a l)
    (cond
      ((null? l) '())
      ((eq? (car l) 'sauce) (cons a(cdr l)))
      (else (cons (car l) (subst-sauce a (cdr l)))))))

;; Concrete Abstractions exercise 1.1
12
16
3

;; Concrete Abstractions exercise 1.6
(define turkey-servings
  (lambda (pounds)
    (if (< pounds 0)
          -1
    (if (<= pounds 12)
           (* pounds (/ 3 4))
      (* pounds (/ 1 2))
      ))))

;; Concrete Abstractions exercise 1.7
; a. (puzzle1 a b c) is calculating the value of a+b if b is larger than c, or a+c if c is larger.
; b. (puzzle2 x) is calculating the absoloute value of x (if x is less than zero, it calculates 0-x, which is -x, a positive number. If it is larger than 0, it calculates 0+x, which is the original value of x).

;; Concrete Abstractions exercise 1.14
(define average
  (lambda (x y)
    (/ (+ x y) 2)
    ))