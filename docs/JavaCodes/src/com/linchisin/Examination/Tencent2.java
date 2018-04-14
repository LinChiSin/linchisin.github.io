package com.linchisin.Examination;

/*
#include <cstdio>

int n, m;
void read() {
    scanf("%d %d", &n, &m);
}

bool check(int mid) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += mid;
        if (sum > m) return false;
        mid = mid & 1 ? (mid >> 1) + 1 : mid >> 1;
    }
    return true;
}

void solve() {
    int l = 0, r = m + 1;
    while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (check(mid)) l = mid;
        else r = mid;
    }
    printf("%d\n", l);
}

int main() {
    read();
    solve();
}
 */

public class Tencent2 {
    int n,m;

}
