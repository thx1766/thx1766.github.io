function [c, a, r] = car(A)

% car  A = c a r echelon factorization.
%
% [c, a, r] = car(A) gives echelon bases for the column space in c
% and the row space in r
% r contains the nonzero rows of the echelon form rref(A)
% c contains the nonzero columns of the echelon form rref(A')'
% All extra nonzeros are below I in c and to the right of I in r
% a is the nonsingular submatrix formed by the pivot columns and 
% pivot rows of A.  Those columns of r and rows of c contain I.
%
% See also elim, rref.

[R, pivcol] = rref(A);
[S, pivrow] = rref(A');
r = R(1:rank(A), : );
c = S(1:rank(A), : )';
a = A(pivrow, pivcol);
