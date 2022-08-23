package com.dekeyserjarno.hoefsmidapp.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.dekeyserjarno.hoefsmidapp.objects.enums.InvoiceOption;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.Company;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.InvoiceItem;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.InvoiceLine;
import com.dekeyserjarno.hoefsmidapp.objects.user.Address;
import com.dekeyserjarno.hoefsmidapp.objects.user.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

    private Workbook workbook;
    private Sheet sheet;
    private int[] rowcol = {0, 0};
    private OutputStream fileOut;

    public void createInvoice(Invoice invoice) throws IOException {
        fileOut =
                Files.newOutputStream(Path.of("C:\\Users\\jarno\\Documents\\invoice.xlsx"));
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        Row row = sheet.createRow(rowcol[0]);


        // header kolom
        CellRangeAddress[] rangedAddresses = {
                // create cell for logo

                CellRangeAddress.valueOf("A1:D8"),
                // cells for business data
                CellRangeAddress.valueOf("F2:I2"),
                CellRangeAddress.valueOf("F3:I3"),
                CellRangeAddress.valueOf("F4:I4"),
                CellRangeAddress.valueOf("F5:I5"),
                CellRangeAddress.valueOf("F6:I6"),
                CellRangeAddress.valueOf("F7:I7"),

                // "Factuur"
                CellRangeAddress.valueOf("A10:B10"),

                // OrderId
                CellRangeAddress.valueOf("C10:D10"),

                // klantendata
                CellRangeAddress.valueOf("A12:C12"),
                CellRangeAddress.valueOf("A13:C13"),
                CellRangeAddress.valueOf("A14:C14"),
                CellRangeAddress.valueOf("A15:C15"),
                CellRangeAddress.valueOf("A16:C16"),

                // vervaldatum 18
                CellRangeAddress.valueOf("A18:B18"),
                CellRangeAddress.valueOf("A20:C20"),
                CellRangeAddress.valueOf("D20:E20"),
                CellRangeAddress.valueOf("F20:G20"),
                CellRangeAddress.valueOf("H20:I20")
        };

        for (CellRangeAddress add : rangedAddresses) {
            sheet.addMergedRegion(add);
        }
        rowcol[0] = 20;
        rowcol[1] = 1;



        CellStyle factuurheader = workbook.createCellStyle();
        Font bigFat = workbook.createFont();
        bigFat.setBold(true);

        bigFat.setFontHeightInPoints((short) 24);
        factuurheader.setFont(bigFat);

        CellStyle boldSender = workbook.createCellStyle();
        Font bol = workbook.createFont();
        bol.setBold(true);
        boldSender.setFont(bol);
        boldSender.setBorderLeft(BorderStyle.THICK);

        CellStyle sender = workbook.createCellStyle();
        sender.setBorderLeft(BorderStyle.THICK);

        Font normal = workbook.createFont();
        normal.setFontHeightInPoints((short) 12);

        CellStyle bigLeft = workbook.createCellStyle();
        bigLeft.setBorderLeft(BorderStyle.THICK);

        Font headerText = workbook.createFont();
        headerText.setBold(true);

        CellStyle header = workbook.createCellStyle();
        header.setFont(headerText);
        header.setBorderBottom(BorderStyle.THICK);
        header.setAlignment(HorizontalAlignment.CENTER);
        header.setVerticalAlignment(VerticalAlignment.BOTTOM);
        header.setBorderTop(BorderStyle.THIN);
        header.setBorderLeft(BorderStyle.THIN);
        header.setBorderRight(BorderStyle.THIN);

        CellStyle fullBorder = workbook.createCellStyle();
        fullBorder.setBorderLeft(BorderStyle.THIN);
        fullBorder.setBorderRight(BorderStyle.THIN);
        fullBorder.setBorderTop(BorderStyle.THIN);
        fullBorder.setBorderBottom(BorderStyle.THIN);

        CellStyle fullBorderexleft = workbook.createCellStyle();
        fullBorderexleft.setBorderRight(BorderStyle.THIN);
        fullBorderexleft.setBorderTop(BorderStyle.THIN);
        fullBorderexleft.setBorderBottom(BorderStyle.THIN);
        fullBorderexleft.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle fullBorderexright = workbook.createCellStyle();
        fullBorderexright.setBorderLeft(BorderStyle.THIN);
        fullBorderexright.setBorderTop(BorderStyle.THIN);
        fullBorderexright.setBorderBottom(BorderStyle.THIN);

        CellStyle bottomtop = workbook.createCellStyle();
        bottomtop.setBorderTop(BorderStyle.THIN);
        bottomtop.setBorderBottom(BorderStyle.THIN);

        CellStyle itemleft = workbook.createCellStyle();
        itemleft.setBorderLeft(BorderStyle.THIN);

        CellStyle itemright = workbook.createCellStyle();
        itemright.setBorderRight(BorderStyle.THIN);

        CellStyle itemEnd = workbook.createCellStyle();
        itemEnd.setBorderTop(BorderStyle.THIN);

        row = sheet.createRow(1);
        Cell cell = row.createCell(5);
        cell.setCellValue(invoice.getCompany().getName());
        cell.setCellStyle(boldSender);

        row = sheet.createRow(2);
        cell = row.createCell(5);
        cell.setCellValue(invoice.getCompany().getAddress().getStreet() + " " + invoice.getCompany().getAddress().getHouseNumber());
        cell.setCellStyle(sender);


        row = sheet.createRow(3);
        cell = row.createCell(5);
        cell.setCellValue(invoice.getCompany().getAddress().getPostalCode() + " " + invoice.getCompany().getAddress().getCity());
        cell.setCellStyle(sender);

        row = sheet.createRow(4);
        cell = row.createCell(5);
        cell.setCellValue(invoice.getCompany().getAddress().getProvince());
        cell.setCellStyle(sender);

        row = sheet.createRow(5);
        cell = row.createCell(5);
        cell.setCellValue(invoice.getCompany().getAddress().getCountry());
        cell.setCellStyle(sender);

        row = sheet.createRow(9);
        cell = row.createCell(0);
        cell.setCellStyle(factuurheader);
        cell.setCellValue("Factuur");

        cell = row.createCell(2);
        cell.setCellValue(invoice.getFollowNumber());
        cell.setCellStyle(factuurheader);

        User buyer = invoice.getBuyer();
        row = sheet.createRow(11);
        cell = row.createCell(0);
        cell.setCellValue(buyer.getFirstname() + " " + buyer.getLastname());
        row = sheet.createRow(12);
        cell = row.createCell(0);
        cell.setCellValue(buyer.getAddress().getStreet() + " " + buyer.getAddress().getHouseNumber());
        row = sheet.createRow(13);
        cell = row.createCell(0);
        cell.setCellValue(buyer.getAddress().getPostalCode() + " " + buyer.getAddress().getCity());
        row = sheet.createRow(14);
        cell = row.createCell(0);
        cell.setCellValue(buyer.getAddress().getProvince());
        row = sheet.createRow(15);
        cell = row.createCell(0);
        cell.setCellValue(buyer.getAddress().getCountry());

        row = sheet.createRow(17);
        cell = row.createCell(0);
        cell.setCellValue("verval datum:");
        cell = row.createCell(2);
        cell.setCellValue(invoice.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


        row = sheet.createRow(19);

        for (int i = 0; i <= 8; i++) {
            cell = row.createCell(i);
            if (i == 0) {
                cell.setCellValue("Beschrijving");
            }
            if (i == 3) {
                cell.setCellValue("Hoeveelheid");
            }
            if (i == 5) {
                cell.setCellValue("BTW");
            }
            if (i == 7) {
                cell.setCellValue("Prijs per stuk");
            }
            cell.setCellStyle(header);
        }


        for (InvoiceLine invoiceLine : invoice.getInvoiceLines()) {
            int rownr = rowcol[0];
            row = sheet.createRow(rownr);
            InvoiceItem item = invoiceLine.getInvoiceItem();

            cell = row.createCell(0);
            cell.setCellValue(item.getName());
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 0, 2));
            cell.setCellStyle(itemleft);

            cell = row.createCell(3);
            cell.setCellValue(invoiceLine.getAmount());
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 3, 4));
            cell.setCellStyle(itemleft);

            cell = row.createCell(5);
            cell.setCellValue(item.getVtaValue());
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 5, 6));
            cell.setCellStyle(itemleft);


            cell = row.createCell(7);
            cell.setCellValue("€ " + item.getUnitPrice().doubleValue());
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 7, 8));
            cell.setCellStyle(itemleft);

            row.createCell(8).setCellStyle(itemright);

            rowcol[0]++;

        }
        for (int i = 0; i < 3; i++) {
            row = sheet.createRow(rowcol[0]);
            int rownr = rowcol[0];

            cell = row.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 0, 2));
            cell.setCellStyle(itemleft);


            cell = row.createCell(3);
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 3, 4));
            cell.setCellStyle(itemleft);

            cell = row.createCell(5);
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 5, 6));
            cell.setCellStyle(itemleft);

            cell = row.createCell(7);
            sheet.addMergedRegion(new CellRangeAddress(rownr, rownr, 7, 8));
            cell.setCellStyle(itemleft);

            row.createCell(8).setCellStyle(itemright);
            rowcol[0]++;

        }

        row = sheet.createRow(rowcol[0]);

        for (int i = 0; i <= 8; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(itemEnd);
        }
        rowcol[0] += 3;

        row = sheet.createRow(rowcol[0]);
        cell = row.createCell(0);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("BTW Code");

        cell = row.createCell(1);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("0");

        cell = row.createCell(2);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("6");

        cell = row.createCell(3);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("12");

        cell = row.createCell(4);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("21");

        cell = row.createCell(5);
        cell.setCellStyle(fullBorderexright);

        cell = row.createCell(6);
        cell.setCellStyle(bottomtop);

        cell = row.createCell(7);
        cell.setCellStyle(bottomtop);
        cell = row.createCell(8);
        cell.setCellStyle(fullBorderexleft);

        rowcol[0]++;
        row = sheet.createRow(rowcol[0]);
        cell = row.createCell(0);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("Bedrag");

        cell = row.createCell(1);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculatePrice(invoice, 0));

        cell = row.createCell(2);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculatePrice(invoice, 6));


        cell = row.createCell(3);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculatePrice(invoice, 12));

        cell = row.createCell(4);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculatePrice(invoice, 21));


        cell = row.createCell(5);
        cell.setCellStyle(fullBorderexright);
        cell.setCellValue("Subtotaal");
        sheet.addMergedRegion(new CellRangeAddress(rowcol[0], rowcol[0], 5, 6));

        cell = row.createCell(6);
        cell.setCellStyle(fullBorderexleft);

        cell = row.createCell(7);
        cell.setCellStyle(fullBorderexright);
        cell.setCellValue("€ ");
        cell = row.createCell(8);
        cell.setCellStyle(fullBorderexleft);

        cell.setCellValue(invoice.getInvoiceLines()
                .stream()
                .filter(s -> s.getInvoiceItem() != null)
                .map(s -> s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()).mapToDouble(s -> s).sum());

        rowcol[0]++;
        row = sheet.createRow(rowcol[0]);
        cell = row.createCell(0);
        cell.setCellStyle(fullBorder);
        cell.setCellValue("BTW");

        cell = row.createCell(1);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(0);

        cell = row.createCell(2);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculateBtw(invoice, 0.06));


        cell = row.createCell(3);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculateBtw(invoice, 0.12));

        cell = row.createCell(4);
        cell.setCellStyle(fullBorder);
        cell.setCellValue(calculateBtw(invoice, 0.21));


        cell = row.createCell(5);
        cell.setCellStyle(fullBorderexright);
        cell.setCellValue("Totaal BTW");
        sheet.addMergedRegion(new CellRangeAddress(rowcol[0], rowcol[0], 5, 6));

        cell = row.createCell(6);
        cell.setCellStyle(fullBorderexleft);

        cell = row.createCell(7);
        cell.setCellStyle(fullBorderexright);
        cell.setCellValue("€ ");
        cell = row.createCell(8);
        cell.setCellStyle(fullBorderexleft);
        cell.setCellValue(
                calculateBtw(invoice, 0.06) + calculateBtw(invoice, 0.12) + calculateBtw(invoice, 0.21)
        );

        rowcol[0]++;
        row = sheet.createRow(rowcol[0]);
        cell = row.createCell(5);
        cell.setCellValue("totaal");
        cell.setCellStyle(fullBorderexright);

        cell = row.createCell(6);
        cell.setCellStyle(fullBorderexleft);


        sheet.addMergedRegion(new CellRangeAddress(rowcol[0], rowcol[0], 5, 6));

        cell = row.createCell(7);
        cell.setCellValue("€ ");
        cell.setCellStyle(fullBorderexright);


        cell = row.createCell(8);
        cell.setCellValue(invoice.calculatePrice());
        cell.setCellStyle(fullBorderexleft);


        workbook.write(fileOut);
        workbook.close();

    }

    private double calculatePrice(Invoice invoice, int btwfilter) {

        return invoice.getInvoiceLines()
                .stream()
                .filter(s -> s.getInvoiceItem() != null)
                .filter(s -> s.getInvoiceItem().getVtaValue() == btwfilter)
                .map(s -> s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()).mapToDouble(s -> s).sum();
    }

    private double calculateBtw(Invoice invoice, double btwfilter) {
        return invoice.getInvoiceLines()
                .stream()
                .filter(s -> s.getInvoiceItem() != null)
                .filter(s -> s.getInvoiceItem().getVtaValue() == (100 * btwfilter))
                .map(s -> s.getAmount() * (s.getInvoiceItem().getUnitPrice().doubleValue() * btwfilter)).mapToDouble(s -> s).sum();
    }


    public void transformExcel(Invoice invoice) throws IOException {

        InputStream file = getClass().getClassLoader().getResourceAsStream("invoice_template.xlsx");
        fileOut = new FileOutputStream("C:\\Users\\jarno\\Documents\\invoice_import.xlsx");

        Workbook wb = new XSSFWorkbook(Objects.requireNonNull(file));
        Sheet sht = wb.getSheetAt(0);

        // determine max size of cells (column)
        int maxLength = Integer.MIN_VALUE;
        List<Row> rows = new ArrayList<>();
        for (int i = 0; i <= sht.getLastRowNum(); i++) {
            Row row = sht.getRow(i);
            if (row == null) {
                row = sht.createRow(i);
            }
            rows.add(sht.getRow(i));
            if (row.getLastCellNum() > maxLength) {
                maxLength = row.getLastCellNum();
            }
        }

        // reading excel
        List<Cellvalue> values = new ArrayList<>();
        for (Row row : rows) {
            for (int i = 0; i < maxLength; i++) {

                Cell cell = row.getCell(i) == null ? row.createCell(i) : row.getCell(i);

                Cellvalue cellvalue =
                        new Cellvalue.CellBuilder()
                                .cellNumber(i)
                                .rowNumber(row.getRowNum())
                                .value(cell.toString())
                                .cellStyle(cell.getCellStyle())
                                .build();
                values.add(cellvalue);
            }
        }

        Row itemRow = null;

        // fill non-items
        for (Cellvalue cellvalue : values) {
            Row row = sht.getRow(cellvalue.rowNumber);
            Cell cell = row.getCell(cellvalue.cellNumber);

            cell.setCellStyle(cellvalue.cellStyle);
            cell.setCellValue(
                    insertData(cellvalue.value, invoice));

            if (cell.toString().contains("item")) {
                itemRow = row;
            }
        }


        // make room for invoice item
        sht.shiftRows(itemRow.getRowNum() + 1, sht.getLastRowNum(), invoice.getInvoiceLines().size() - 1);

        // create invoice item in new excel
        for (int position = 0; position < invoice.getInvoiceLines().size(); position++) {
            int rowNumber = position + itemRow.getRowNum();
            Row current = sht.getRow(rowNumber) == null ? sht.createRow(rowNumber) : sht.getRow(rowNumber);
            for (int i = 0; i <= maxLength; i++) {
                Row next = sht.getRow(current.getRowNum() + 1) == null ? sht.createRow(current.getRowNum() + 1) : sht.getRow(current.getRowNum() + 1);

                Cell cell = current.getCell(i) == null ? current.createCell(i) : current.getCell(i);
                Cell nextcell = next.getCell(i) == null ? next.createCell(i) : next.getCell(i);
                if(Arrays.stream(cell.toString().split(" ")).filter(s -> s.trim() .equalsIgnoreCase("item")).findAny().isPresent()){
                    CellRangeAddress address = new CellRangeAddress(rowNumber,rowNumber,i,i + 3);
                    sht.addMergedRegion(address);
                }

                if (position < invoice.getInvoiceLines().size()-1) {
                    nextcell.setCellValue(cell.toString());
                }
                nextcell.setCellStyle(cell.getCellStyle());

                cell.setCellValue(insertItem(current.getCell(i).toString(), invoice.getInvoiceLines().get(position)));
            }

        }


        wb.write(fileOut);
        wb.close();

    }


    private String insertItem(String val, InvoiceLine line) {

        StringBuilder result = new StringBuilder();
        String[] split = val.split(" ");
        for (String va : split) {
            if (Arrays.stream(InvoiceOption.values()).noneMatch(s -> s.name().equalsIgnoreCase(va.toLowerCase().trim()))) {
                result.append(va);
            } else {
                InvoiceOption option = InvoiceOption.valueOf(va.toLowerCase().trim());
                switch (option) {
                    case unitprice:
                        result.append(line.getInvoiceItem().getUnitPrice().toString()).append(" ");
                        break;
                    case amount:
                        result.append(line.getAmount()).append(" ");
                        break;
                    case vta:
                        result.append(line.getInvoiceItem().getVtaValue()).append(" ");
                        break;
                    case totalitem:
                        result.append(((double)line.getAmount()*line.getInvoiceItem()
                                .getUnitPrice().doubleValue()) *
                                (1 + ((double) line.getInvoiceItem().getVtaValue() / 100)))
                                .append(" ");
                        break;
                    case totalitemexcl:
                        result.append(line.getAmount() * line.getInvoiceItem().getUnitPrice().doubleValue()).append(" ");
                        break;
                    case item:
                        result.append(line.getInvoiceItem().getName()).append(" ");
                        break;
                    case english:
                        result.append(line.getInvoiceItem().getEnglishName()).append(" ");
                        break;
                }
            }
        }

        return result.toString();

    }


    private String insertData(String val, Invoice invoice) {


        Company source = invoice.getCompany();
        Address saddress = source.getAddress();

        User target = invoice.getBuyer();
        Address taddress = target.getAddress();

        String[] values = val.replace("\n", "").split(" ");
        StringBuilder result = new StringBuilder();

        for (String va : values) {
            String finalVa = va.trim().toLowerCase();
            if (Arrays.stream(InvoiceOption.values()).noneMatch(s -> s.name().equalsIgnoreCase(finalVa.trim().toLowerCase()))) {
                if(isNummeric(va)){

                    int intvalue = (int)Double.parseDouble(va);
                    va = String.valueOf(intvalue);
                }
                result.append(va).append(" ");

            } else {
                InvoiceOption option = InvoiceOption.valueOf(va);

                switch (option) {
                    case totalvta:
                        result.append(((Double) invoice.getInvoiceLines().stream()
                                .map(s -> s.getInvoiceItem().getUnitPrice().doubleValue() * s.getAmount())
                                .mapToDouble(s -> s).sum()));
                        break;
                    case total:
                        result.append(invoice.getInvoiceLines().stream()
                                .map(s -> s.getAmount() * (s.getInvoiceItem().getUnitPrice().doubleValue()
                                        * (1 + ((double) s.getInvoiceItem().getVtaValue() / 100))))
                                .mapToDouble(s -> s).sum()
                        ).append(" ");
                        break;
                    case total0incl:
                        result.append(calculateBtw(invoice, 0)).append(" ");
                        break;
                    case total6incl:
                        result.append(calculateBtw(invoice, 0.06)).append(" ");

                        break;
                    case total12incl:
                        result.append(calculateBtw(invoice, 0.12)).append(" ");
                        break;
                    case total21incl:
                        result.append(calculateBtw(invoice, 0.21)).append(" ");
                        break;
                    case total0:
                        result.append(calculatePrice(invoice, 0)).append(" ");
                        break;
                    case total6:
                        result.append(calculatePrice(invoice, 6)).append(" ");

                        break;
                    case total12:
                        result.append(calculatePrice(invoice, 12)).append(" ");
                        break;
                    case total21:
                        result.append(calculatePrice(invoice, 21)).append(" ");
                        break;

                    case total0vta:
                        result.append((Double) invoice.getInvoiceLines().stream()
                                .filter(s -> s.getInvoiceItem().getVtaValue() == 0)
                                .map(s ->
                                        s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()
                                                * ((double) s.getInvoiceItem().getVtaValue() / 100)).mapToDouble(s -> s).sum())
                                .append(" ");
                        break;
                    case total6vta:
                        result.append((Double) invoice.getInvoiceLines().stream()
                                .filter(s -> s.getInvoiceItem().getVtaValue() == 6)
                                .map(s ->
                                        s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()
                                                * ((double) s.getInvoiceItem().getVtaValue() / 100)).mapToDouble(s -> s).sum())
                                .append(" ");
                        break;
                    case total12vta:
                        result.append((Double) invoice.getInvoiceLines().stream()
                                .filter(s -> s.getInvoiceItem().getVtaValue() == 12)
                                .map(s ->
                                        s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()
                                                * ((double) s.getInvoiceItem().getVtaValue() / 100)).mapToDouble(s -> s).sum())
                                .append(" ");
                        break;
                    case total21vta:
                        result.append((Double) invoice.getInvoiceLines().stream()
                                .filter(s -> s.getInvoiceItem().getVtaValue() == 21)
                                .map(s ->
                                        s.getAmount() * s.getInvoiceItem().getUnitPrice().doubleValue()
                                                * ((double) s.getInvoiceItem().getVtaValue() / 100)).mapToDouble(s -> s).sum())
                                .append(" ");
                        break;


                    case sourcecity:
                        result.append(saddress.getCity()).append(" ");
                        break;
                    case sourcehousenumber:
                        result.append(saddress.getHouseNumber()).append(" ");
                        break;
                    case sourcestreet:
                        result.append(saddress.getStreet()).append(" ");
                        break;
                    case sourcepostalcode:
                        result.append(saddress.getPostalCode()).append(" ");
                        break;
                    case sourcecountry:
                        result.append(saddress.getCountry()).append(" ");
                        break;
                    case sourcename:
                        result.append(source.getName()).append(" ");
                        break;
                    case sourceprovince:
                        result.append(saddress.getProvince()).append(" ");

                        break;
                    case targetcity:
                        result.append(taddress.getCity()).append(" ");
                        break;
                    case targethousenumber:
                        result.append(taddress.getHouseNumber()).append(" ");
                        break;
                    case targetstreet:
                        result.append(taddress.getStreet()).append(" ");
                        break;
                    case targetpostalcode:
                        result.append(taddress.getPostalCode()).append(" ");
                        break;
                    case targetcountry:
                        result.append(taddress.getCountry()).append(" ");
                        break;
                    case targetname:
                        result.append(target.getFirstname()).append(target.getLastname()).append(" ");
                        break;
                    case targetprovince:
                        result.append(taddress.getProvince()).append(" ");
                        break;
                    case sourcebenumber:
                        result.append(source.getAccountNumber()).append(" ");
                        break;
                    case invoicenumber:
                        result.append(invoice.getFollowNumber()).append(" ");
                        break;
                    case logo:
                        result.append(source.getLogoPath()).append(" ");
                        break;
                    case totalexclvta:
                        result.append(calculateBtw(invoice, 0) + calculateBtw(invoice, 0.06) + calculateBtw(invoice, 0.12) + calculateBtw(invoice, 0.21)).append(" ");
                        break;
                    case expirationdate:
                        result.append(invoice.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(" ");
                        break;
                    default:
                        result.append(va).append(" ");
                        break;
                }
            }

        }
        return result.toString();

    }

    private boolean isNummeric(String va) {
        if(va == null){
            return false;
        }

        try{
            double value = Double.parseDouble(va);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    private static class Cellvalue {
        private final int rowNumber;
        private final int cellNumber;
        private final String value;
        private final CellStyle cellStyle;

        private Cellvalue(CellBuilder builder) {
            this.rowNumber = builder.row;
            this.cellNumber = builder.cell;
            this.value = builder.value;
            this.cellStyle = builder.cellStyle;

        }


        public static class CellBuilder {
            private int row;
            private int cell;
            private String value;
            private CellStyle cellStyle;

            public CellBuilder rowNumber(int rowNumber) {
                this.row = rowNumber;
                return this;
            }

            public CellBuilder cellNumber(int cell) {
                this.cell = cell;
                return this;
            }

            public CellBuilder value(String value) {

                this.value = value;
                return this;
            }

            public CellBuilder cellStyle(CellStyle cellStyle) {
                this.cellStyle = cellStyle;
                return this;
            }

            public Cellvalue build() {
                return new Cellvalue(this);
            }

        }
    }

}
