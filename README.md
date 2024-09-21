# Mahasiswa ID

**Repository diarsipkan** karena materi Mobpro 2 mulai September 2024 sudah menggunakan Jetpack Compose untuk layout, dan menggunakan studi kasus yang berbeda. Untuk melihat project terbaru, silahkan kunjungi repo [indraazimi/mobpro2-compose](https://github.com/indraazimi/mobpro2-compose) 😊

..

Aplikasi Android yang menampilkan data mahasiswa dalam bentuk daftar. Menggunakan custom dialog untuk input data dan action mode agar dapat menghapus banyak data sekaligus.

Custom Dialog                                        | Tampilan Data                                        | Action Mode
-----------------------------------------------------|------------------------------------------------------|---------------------------------------------------
<img src="screenshots/dialog-tambah.png" width="200">|<img src="screenshots/list-mahasiswa.png" width="200">|<img src="screenshots/action-mode.png" width="200">

Aplikasi ini menggunakan Room untuk data persistence. Terdapat pengembangan aplikasi ini di branch lain:
- Branch testing: versi project dengan pengujian otomatis (instrumented testing) menggunakan Espresso
- Branch firebase: versi project yang menggunakan Firebase Realtime Database sebagai persistence
- Branch two-pane: versi project dengan navigasi master-detail dan two-pane layout di tablet

Punya pertanyaan atau masukan? Silahkan disampaikan melalui issue repository ini.

## Lisensi

    Copyright (c) 2020 Indra Azimi. All rights reserved.

    Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
    Dilarang melakukan penggandaan dan atau komersialisasi,
    sebagian atau seluruh bagian, baik cetak maupun elektronik
    terhadap project ini tanpa izin pemilik hak cipta.
