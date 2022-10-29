/*
 * This file is generated using FunctionOperatorsGeneratorKt.main()
 */

@file:Suppress("unused", "KDocMissingDocumentation")

val <A, B, R> ((A, B) -> R).curry: (A) -> (B) -> R
    get() = fun(a: A) = fun(b: B) = this(a, b)

val <A, B, R> ((A) -> (B) -> R).curry: (A, B) -> R
    get() = fun(a: A, b: B) = this(a)(b)


val <A, B, C, R> ((A, B, C) -> R).curry: (A) -> (B) -> (C) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = this(a, b, c)

val <A, B, C, R> ((A) -> (B) -> (C) -> R).curry: (A, B, C) -> R
    get() = fun(a: A, b: B, c: C) = this(a)(b)(c)


val <A, B, C, D, R> ((A, B, C, D) -> R).curry: (A) -> (B) -> (C) -> (D) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = this(a, b, c, d)

val <A, B, C, D, R> ((A) -> (B) -> (C) -> (D) -> R).curry: (A, B, C, D) -> R
    get() = fun(a: A, b: B, c: C, d: D) = this(a)(b)(c)(d)


val <A, B, C, D, E, R> ((A, B, C, D, E) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = this(a, b, c, d, e)

val <A, B, C, D, E, R> ((A) -> (B) -> (C) -> (D) -> (E) -> R).curry: (A, B, C, D, E) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E) = this(a)(b)(c)(d)(e)


val <A, B, C, D, E, F, R> ((A, B, C, D, E, F) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = this(a, b, c, d, e, f)

val <A, B, C, D, E, F, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> R).curry: (A, B, C, D, E, F) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F) = this(a)(b)(c)(d)(e)(f)


val <A, B, C, D, E, F, G, R> ((A, B, C, D, E, F, G) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> R
    get() = fun(a: A) =
        fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = this(a, b, c, d, e, f, g)

val <A, B, C, D, E, F, G, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> R).curry: (A, B, C, D, E, F, G) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G) = this(a)(b)(c)(d)(e)(f)(g)


val <A, B, C, D, E, F, G, H, R> ((A, B, C, D, E, F, G, H) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> R
    get() = fun(a: A) =
        fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = this(a, b, c, d, e, f, g, h)

val <A, B, C, D, E, F, G, H, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> R).curry: (A, B, C, D, E, F, G, H) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H) = this(a)(b)(c)(d)(e)(f)(g)(h)


val <A, B, C, D, E, F, G, H, I, R> ((A, B, C, D, E, F, G, H, I) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) =
        fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) = this(a, b, c, d, e, f, g, h, i)

val <A, B, C, D, E, F, G, H, I, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> R).curry: (A, B, C, D, E, F, G, H, I) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I) = this(a)(b)(c)(d)(e)(f)(g)(h)(i)


val <A, B, C, D, E, F, G, H, I, J, R> ((A, B, C, D, E, F, G, H, I, J) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) =
        fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) = fun(j: J) = this(a, b, c, d, e, f, g, h, i, j)

val <A, B, C, D, E, F, G, H, I, J, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> R).curry: (A, B, C, D, E, F, G, H, I, J) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J) = this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)


val <A, B, C, D, E, F, G, H, I, J, K, R> ((A, B, C, D, E, F, G, H, I, J, K) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) =
        fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) = fun(j: J) = fun(k: K) = this(a, b, c, d, e, f, g, h, i, j, k)

val <A, B, C, D, E, F, G, H, I, J, K, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)


val <A, B, C, D, E, F, G, H, I, J, K, L, R> ((A, B, C, D, E, F, G, H, I, J, K, L) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) =
        fun(g: G) = fun(h: H) = fun(i: I) = fun(j: J) = fun(k: K) = fun(l: L) = this(a, b, c, d, e, f, g, h, i, j, k, l)

val <A, B, C, D, E, F, G, H, I, J, K, L, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K, L) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)


val <A, B, C, D, E, F, G, H, I, J, K, L, M, R> ((A, B, C, D, E, F, G, H, I, J, K, L, M) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) =
        fun(i: I) = fun(j: J) = fun(k: K) = fun(l: L) = fun(m: M) = this(a, b, c, d, e, f, g, h, i, j, k, l, m)

val <A, B, C, D, E, F, G, H, I, J, K, L, M, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K, L, M) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)(m)


val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, R> ((A, B, C, D, E, F, G, H, I, J, K, L, M, N) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) =
        fun(j: J) = fun(k: K) = fun(l: L) = fun(m: M) = fun(n: N) = this(a, b, c, d, e, f, g, h, i, j, k, l, m, n)

val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K, L, M, N) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)(m)(n)


val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, R> ((A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) =
        fun(j: J) = fun(k: K) =
            fun(l: L) = fun(m: M) = fun(n: N) = fun(o: O) = this(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)

val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)(m)(n)(o)


val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, R> ((A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) -> R).curry: (A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> R
    get() = fun(a: A) = fun(b: B) = fun(c: C) = fun(d: D) = fun(e: E) = fun(f: F) = fun(g: G) = fun(h: H) = fun(i: I) =
        fun(j: J) = fun(k: K) = fun(l: L) =
            fun(m: M) = fun(n: N) = fun(o: O) = fun(p: P) = this(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)

val <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, R> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> R).curry: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) -> R
    get() = fun(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P) =
        this(a)(b)(c)(d)(e)(f)(g)(h)(i)(j)(k)(l)(m)(n)(o)(p)


