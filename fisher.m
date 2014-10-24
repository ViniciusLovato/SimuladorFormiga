% Fisher Theorem
function ft = fisherTheorem()
% loading files
generation0;
generation1;
generation2;
generation3;
generation4;
generation5;
generation6;
generation7;
generation8;
generation9;

% calculating values 
ft = [fisher(g0), fisher(g1), fisher(g2), fisher(g3), fisher(g4), fisher(g5), fisher(g6), fisher(g7), fisher(g8), fisher(g9)];
end


function r = fisher(generation)
s = std(generation);
f = mean(generation);
r = s*s/f;
end


% applying the theorem
ans = fisherTheorem();

% saving to a file
dlmwrite("fisher.txt", ans, '\n');
