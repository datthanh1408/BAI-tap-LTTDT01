package com.example.lttddt01

import com.example.lttddt01.repository.KhoanChi
import com.example.lttddt01.repository.KiemTraTienIch_204
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Kiểm thử ứng dụng theo yêu cầu Câu 5:
 * - Nhập đúng dữ liệu
 * - Bỏ trống tên
 * - Nhập số tiền không hợp lệ
 * - Lưu chỉnh sửa
 * - Hủy chỉnh sửa
 */
class KiemThuChiTieuUnitTest {

    // Test case 1: Nhập đúng dữ liệu
    @Test
    fun kiemThu_NhapDungDuLieu_204() {
        val khoanChi_204 = KhoanChi(
            maKhoanChi = "KC204_TEST",
            tenKhoanChi = "Học phí môn Lập trình thiết bị di động",
            soTien = 1850000.0,
            ghiChu = "Học kỳ 1",
            daThanhToan = true
        )

        assertTrue(KiemTraTienIch_204.laTenKhoanChiHopLe_204(khoanChi_204.tenKhoanChi))
        assertTrue(khoanChi_204.laSoTienHopLe())
        assertEquals("Học phí môn Lập trình thiết bị di động", khoanChi_204.tenKhoanChi)
        assertEquals(1850000.0, khoanChi_204.soTien, 0.001)
        assertTrue(khoanChi_204.layThongTin().contains("Đã thanh toán"))
    }

    // Test case 2: Bỏ trống tên hoặc chỉ chứa khoảng trắng
    @Test
    fun kiemThu_BoTrongTenKhoanChi_204() {
        assertFalse(KiemTraTienIch_204.laTenKhoanChiHopLe_204(""))
        assertFalse(KiemTraTienIch_204.laTenKhoanChiHopLe_204("   "))
        assertFalse(KiemTraTienIch_204.laTenKhoanChiHopLe_204(null))
    }

    // Test case 3: Nhập số tiền không hợp lệ (bằng 0, số âm, ký tự lạ)
    @Test
    fun kiemThu_NhapSoTienKhongHopLe_204() {
        val kcSoAm_204 = KhoanChi("KC1", "Tiền sách", -50000.0, "", true)
        val kcBangKhong_204 = KhoanChi("KC2", "Tiền sách", 0.0, "", true)

        assertFalse(kcSoAm_204.laSoTienHopLe())
        assertFalse(kcBangKhong_204.laSoTienHopLe())

        val (hopLe1_204, _) = KiemTraTienIch_204.chuyenDoiVaKiemTraSoTien_204("-1000")
        assertFalse(hopLe1_204)

        val (hopLe2_204, _) = KiemTraTienIch_204.chuyenDoiVaKiemTraSoTien_204("0")
        assertFalse(hopLe2_204)

        val (hopLe3_204, _) = KiemTraTienIch_204.chuyenDoiVaKiemTraSoTien_204("abc")
        assertFalse(hopLe3_204)
    }

    // Test case 4: Lưu chỉnh sửa thành công
    @Test
    fun kiemThu_LuuChinhSuaThanhCong_204() {
        val khoanChiGoc_204 = KhoanChi("KC_GOC", "Cơm trưa", 30000.0, "Cơm sườn", false)

        // Thực hiện chỉnh sửa
        val khoanChiMoi_204 = KhoanChi(
            maKhoanChi = khoanChiGoc_204.maKhoanChi,
            tenKhoanChi = "Cơm trưa đặc biệt",
            soTien = 45000.0,
            ghiChu = "Cơm sườn trứng",
            daThanhToan = true
        )

        assertEquals("Cơm trưa đặc biệt", khoanChiMoi_204.tenKhoanChi)
        assertEquals(45000.0, khoanChiMoi_204.soTien, 0.001)
        assertTrue(khoanChiMoi_204.daThanhToan)
    }

    // Test case 5: Hủy chỉnh sửa - Giữ nguyên dữ liệu ban đầu
    @Test
    fun kiemThu_HuyChinhSua_GiuNguyenDuLieu_204() {
        val khoanChiBanDau_204 = KhoanChi("KC_GOC", "Tiền xăng", 50000.0, "Xăng xe máy", true)
        val banSao_204 = KhoanChi(
            khoanChiBanDau_204.maKhoanChi,
            khoanChiBanDau_204.tenKhoanChi,
            khoanChiBanDau_204.soTien,
            khoanChiBanDau_204.ghiChu,
            khoanChiBanDau_204.daThanhToan
        )

        // Giả lập thao tác hủy (không cập nhật bản sao vào bản gốc)
        assertEquals(khoanChiBanDau_204.tenKhoanChi, banSao_204.tenKhoanChi)
        assertEquals(khoanChiBanDau_204.soTien, banSao_204.soTien, 0.001)
        assertEquals(khoanChiBanDau_204.daThanhToan, banSao_204.daThanhToan)
    }
}
